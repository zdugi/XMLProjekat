package com.organ.project_organ.controller;

import com.itextpdf.text.DocumentException;
import com.organ.project_organ.model.xml_zahtev.Zahtev;
import com.organ.project_organ.pojo.ObavestenjeDTO;
import com.organ.project_organ.service.ObavestenjeService;
import com.organ.project_organ.service.ZahtevService;
import com.organ.project_organ.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/notification")
public class ObavestenjeController {

    @Autowired
    private ObavestenjeService obavestenjeService;

    @Autowired
    private ZahtevService zahtevService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequest(){
        try {
            return new ResponseEntity<>(Converter.fromStringArray(obavestenjeService.getList()), HttpStatus.OK);
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, path = "/{zahtev}")
    public ResponseEntity<?> createRequest(@RequestBody ObavestenjeDTO obavestenjeDTO, @PathVariable String zahtev) {
        try {
            System.out.print(zahtev);
            Zahtev zahtevObavestenje = zahtevService.getOne(zahtev);
            obavestenjeService.create(obavestenjeDTO, zahtevObavestenje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("<Status>OK</Status>", HttpStatus.OK);
    }

    @GetMapping(path = "/xhtml/{id}")
    public ResponseEntity<?> getRequestHTML(@PathVariable String id) throws FileNotFoundException {
        return new ResponseEntity<>(
                obavestenjeService.generateHTML(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestRDF(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(obavestenjeService.getOneRDF(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestJSON(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(obavestenjeService.getOneJSON(id).toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getRequestPDF(@PathVariable("id") String id) throws IOException, DocumentException {
        ByteArrayInputStream bis =
                new ByteArrayInputStream(
                        obavestenjeService.generatePDF(id).toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=request.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
