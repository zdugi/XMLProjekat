package com.xmlproject.project_poverenik.controller;

import com.itextpdf.text.DocumentException;
import com.xmlproject.project_poverenik.model.xml_korisnik.Korisnik;
import com.xmlproject.project_poverenik.model.xml_resenje.Resenje;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.repository.ResenjeRepository;
import com.xmlproject.project_poverenik.service.ResenjeService;
import com.xmlproject.project_poverenik.util.Converter;
import com.xmlproject.project_poverenik.ws.zahtev.ZahtevInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import pojo.ComplaintsAdvanceSearchQuery;
import pojo.ComplaintsListDTO;
import pojo.ResenjeDTO;
import pojo.ResolutionsAdvanceSearchQuery;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/api/solution")
public class ResenjeController {
    @Autowired
    private ResenjeService resenjeService;

    //TODO: boilerplate
    @GetMapping(path = "/request/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestOverSOAP(@PathVariable String id) {
        try {
            URL wsdlLocation = new URL("http://localhost:8089/ws/request?wsdl");
            QName serviceName = new QName("http://soap.spring.com/ws/request", "ZahtevService");
            QName portName = new QName("http://soap.spring.com/ws/request", "ZahtevPort");

            javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);

            ZahtevInterface zahtevInterface = service.getPort(portName, ZahtevInterface.class);

            return new ResponseEntity<>(zahtevInterface.getRequest(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Dosle je do greske prilikom pribavljanja zahteva putem WS. (ovo se desava i kad zahtev ne postoji, vraca null)");
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // END

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)    //treba dto
    public ResponseEntity<?> createResenje(@RequestBody ResenjeDTO resenje) {
        try {
            resenjeService.create(resenje);

            return new ResponseEntity<>("<Response><Status>Created</Status></Response>", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>("<Response><Status>Error</Status></Response>", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenje(@PathVariable String id) {
        Resenje resenje = null;
        try {
            resenje = resenjeService.getOne(id);
            return new ResponseEntity<>(resenje, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(resenje, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestsIDList() {
        try {
            return new ResponseEntity<>(Converter.fromStringArray(resenjeService.getList()), HttpStatus.OK);
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getRequestPDF(@PathVariable("id") String id) throws IOException, DocumentException {
        ByteArrayInputStream bis =
                new ByteArrayInputStream(
                        resenjeService.generatePDF(id).toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=request.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getSolutionIDListUser() {
        Korisnik userDetails = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResolutionsAdvanceSearchQuery query = new ResolutionsAdvanceSearchQuery();
        query.datumPodnosenjaZahteva = "";
        query.doneseno = "";
        query.prihvacena = "";
        query.resenjeZa = "";
        query.upucujeSe = "";
        query.zalilac = userDetails.getId();
        return new ResponseEntity<>(resenjeService.queryRDF(query).toString(), HttpStatus.OK);


        //return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/xhtml/{id}")
    public ResponseEntity<?> getSolutionHTML(@PathVariable String id) throws FileNotFoundException {
        return new ResponseEntity<>(
                resenjeService.generateHTML(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestRDF(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(resenjeService.getOneRDF(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestJSON(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(resenjeService.getOneJSON(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/simple-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> simpleSearch(@RequestParam String query) {
        if (query == null || query.trim().isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);

        ComplaintsListDTO resources = resenjeService.searchText(query);

        if (resources == null)
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity(resources, HttpStatus.OK);
    }

    @PostMapping(path = "/advance-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> advanceSearch(@RequestBody ResolutionsAdvanceSearchQuery query) {
        System.out.println("primio");
        if (query.datumPodnosenjaZahteva.isEmpty() && query.resenjeZa.isEmpty() &&
                query.doneseno.isEmpty() && query.prihvacena.isEmpty() &&
                query.upucujeSe.isEmpty() && query.zalilac.isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resenjeService.queryRDF(query).toString(), HttpStatus.OK);
    }

}
