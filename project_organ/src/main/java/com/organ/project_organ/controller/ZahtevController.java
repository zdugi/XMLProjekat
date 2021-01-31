package com.organ.project_organ.controller;

import com.itextpdf.text.DocumentException;
import com.organ.project_organ.pojo.*;
import com.organ.project_organ.service.*;
import com.organ.project_organ.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.print.attribute.standard.Media;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import java.io.*;

@RestController
@RequestMapping("/api/requests")
public class ZahtevController {
    @Autowired
    private ZahtevService zahtevService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createRequest(@RequestBody ZahtevDokumentDTO zahtevDokumentDTO) {
        try {
            zahtevService.create(zahtevDokumentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("<Status>OK</Status>", HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestsIDList() {
        try {
            return new ResponseEntity<>(Converter.fromStringArray(zahtevService.getList()), HttpStatus.OK);
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
                        zahtevService.generatePDF(id).toByteArray());

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
                zahtevService.generateHTML(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestRDF(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zahtevService.getOneRDF(id).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestJSON(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zahtevService.getOneJSON(id).toString(), HttpStatus.OK);
    }
}
