package com.xmlproject.project_poverenik.controller;

import com.itextpdf.text.DocumentException;
import com.xmlproject.project_poverenik.model.xml_resenje.Resenje;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.repository.ResenjeRepository;
import com.xmlproject.project_poverenik.service.ResenjeService;
import com.xmlproject.project_poverenik.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import pojo.ComplaintsAdvanceSearchQuery;
import pojo.ComplaintsListDTO;
import pojo.ResenjeDTO;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/solution")
public class ResenjeController {
    @Autowired
    private ResenjeService resenjeService;

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

    /*@PostMapping(path = "/advance-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> advanceSearch(@RequestBody ComplaintsAdvanceSearchQuery query) {
        if (query.applicantRegex.isEmpty() && query.submissionDateRegex.isEmpty() &&
                query.authorityRegex.isEmpty() && query.placeRegex.isEmpty() && query.stateRegex.isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resenjeService.queryRDF(query).toString(), HttpStatus.OK);
    }*/

}
