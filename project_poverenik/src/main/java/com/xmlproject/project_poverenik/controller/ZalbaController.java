package com.xmlproject.project_poverenik.controller;

import com.itextpdf.text.DocumentException;
import com.xmlproject.project_poverenik.model.xml_korisnik.Korisnik;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.xmlproject.project_poverenik.service.ZalbaNaCutanjeService;
import com.xmlproject.project_poverenik.service.ZalbaNaOdlukuService;
import com.xmlproject.project_poverenik.util.Converter;
import org.apache.jena.base.Sys;
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
import pojo.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/complaint")
public class ZalbaController {

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;

    @Autowired
    private ZalbaNaOdlukuService zalbaNaOdlukuService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> resolve(@RequestBody ZalbaNaCutanjeDTO zalbaNaCutanje) {
        try {
            zalbaNaCutanjeService.create(zalbaNaCutanje);
            return new ResponseEntity<>("<Response><Status>OK</Status></Response>", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>("<Response><Status>Error</Status></Response>", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalba(@PathVariable String id) {
        ZalbaNaCutanje zalbaNaCutanje = null;
        try {
            zalbaNaCutanje = zalbaNaCutanjeService.getOne(id);
            return new ResponseEntity<>(zalbaNaCutanje, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(zalbaNaCutanje, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestsIDList() {

        try {
            //return new ResponseEntity<>(Converter.fromStringArray(zalbaNaCutanjeService.getList()), HttpStatus.OK);
            return new ResponseEntity<>(Converter.fromZalbe(zalbaNaCutanjeService.getAllXMLInCollection()), HttpStatus.OK);

        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getRequestPDF(@PathVariable("id") String id) throws IOException, DocumentException {
        ByteArrayInputStream bis =
                new ByteArrayInputStream(
                        zalbaNaCutanjeService.generatePDF(id).toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=request.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/xhtml/{id}")
    public ResponseEntity<?> getRequestHTML(@PathVariable String id) throws FileNotFoundException {
        return new ResponseEntity<>(
                zalbaNaCutanjeService.generateHTML(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestRDF(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zalbaNaCutanjeService.getOneRDF(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestJSON(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zalbaNaCutanjeService.getOneJSON(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/simple-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> simpleSearch(@RequestParam String query) {
        if (query == null || query.trim().isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);

        ComplaintsListDTO resources = zalbaNaCutanjeService.searchText(query);

        if (resources == null)
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity(resources, HttpStatus.OK);
    }

    @PostMapping(path = "/advance-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> advanceSearch(@RequestBody ComplaintsAdvanceSearchQuery query) {
        if (query.applicantRegex.isEmpty() && query.submissionDateRegex.isEmpty() &&
                query.authorityRegex.isEmpty() && query.placeRegex.isEmpty() && query.stateRegex.isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(zalbaNaCutanjeService.queryRDF(query).toString(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getComplaintsIDListUser() {
        Korisnik userDetails = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ComplaintsAdvanceSearchQuery query = new ComplaintsAdvanceSearchQuery();
        query.authorityRegex = "";
        query.placeRegex = "";
        query.stateRegex = "";
        query.submissionDateRegex = "";
        query.applicantRegex = userDetails.getId();

        return new ResponseEntity<>(zalbaNaCutanjeService.queryRDF(query).toString(), HttpStatus.OK);


        //return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value="/resolution", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> resolve1(@RequestBody ZalbaNaOdlukuDTO zalbaNaOdluku) {
        try {
            zalbaNaOdlukuService.create(zalbaNaOdluku);
            return new ResponseEntity<>("<Response><Status>OK</Status></Response>", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>("<Response><Status>Error</Status></Response>", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "resolution/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaNaOdluku(@PathVariable String id) {
        ZalbaNaOdluku zalbaNaOdluku = null;
        try {
            zalbaNaOdluku = zalbaNaOdlukuService.getOne(id);
            return new ResponseEntity<>(zalbaNaOdluku, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(zalbaNaOdluku, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "resolution", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestsIDList2() throws Exception {

        for (ZalbaNaOdluku z: zalbaNaOdlukuService.getAllXMLInCollection()){
            System.out.println(z.getId() +  " " + z.getAbout());
        }

        //try {
            //return new ResponseEntity<>(Converter.fromStringArray(zalbaNaOdlukuService.getList()), HttpStatus.OK);
            //ComplaintsAdvanceSearchQuery query = new ComplaintsAdvanceSearchQuery("");

        ComplaintsExtendedDTO cs = Converter.fromZalbe(zalbaNaOdlukuService.getAllXMLInCollection());
        for (ComplaintsExtendedDTO.Complaint c: cs.complaint){
            System.out.println(c.value  + "  " + c.status);
        }
        return new ResponseEntity<>(Converter.fromZalbe(zalbaNaOdlukuService.getAllXMLInCollection()), HttpStatus.OK);

//        return new ResponseEntity<>(zalbaNaOdlukuService.queryRDF(query).toString(), HttpStatus.OK);
        //} catch (XMLDBException e) {
        //    e.printStackTrace();
        //} catch (IllegalAccessException e) {
        //    e.printStackTrace();
        //} catch (InstantiationException e) {
        //    e.printStackTrace();
        //}

        //return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/resolution/user", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestsIDListUser() {
        Korisnik userDetails = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ComplaintsAdvanceSearchQuery query = new ComplaintsAdvanceSearchQuery();
        query.authorityRegex = "";
        query.placeRegex = "";
        query.stateRegex = "";
        query.submissionDateRegex = "";
        query.applicantRegex = userDetails.getId();
        return new ResponseEntity<>(zalbaNaOdlukuService.queryRDF(query).toString(), HttpStatus.OK);


        //return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "resolution/pdf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getRequestPDF2(@PathVariable("id") String id) throws IOException, DocumentException {
        ByteArrayInputStream bis =
                new ByteArrayInputStream(
                        zalbaNaOdlukuService.generatePDF(id).toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=request.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "resolution/xhtml/{id}")
    public ResponseEntity<?> getRequestHTML2(@PathVariable String id) throws FileNotFoundException {
        return new ResponseEntity<>(
                zalbaNaOdlukuService.generateHTML(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "resolution/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestRDF2(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zalbaNaOdlukuService.getOneRDF(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "resolution/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestJSON2(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zalbaNaOdlukuService.getOneJSON(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "resolution/simple-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> simpleSearch2(@RequestParam String query) {
        if (query == null || query.trim().isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);

        ComplaintsListDTO resources = zalbaNaOdlukuService.searchText(query);

        if (resources == null)
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity(resources, HttpStatus.OK);
    }

    @PostMapping(path = "resolution/advance-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> advanceSearch2(@RequestBody ComplaintsAdvanceSearchQuery query) {
        if (query.applicantRegex.isEmpty() && query.submissionDateRegex.isEmpty() &&
                query.authorityRegex.isEmpty() && query.placeRegex.isEmpty() && query.stateRegex.isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(zalbaNaOdlukuService.queryRDF(query).toString(), HttpStatus.OK);
    }
}
