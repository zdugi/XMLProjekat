package com.organ.project_organ.controller;

import com.itextpdf.text.DocumentException;
import com.organ.project_organ.pojo.ReportsAdvanceSearchQuery;
import com.organ.project_organ.pojo.RequestsAdvanceSearchQuery;
import com.organ.project_organ.pojo.ResourcesListDTO;
import com.organ.project_organ.repository.impl.IzvestajRepository;
import com.organ.project_organ.service.IzvestajService;
import com.organ.project_organ.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.ws.Response;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@RestController
@RequestMapping("/api/reports")
public class IzvestajController {
    @Autowired
    private IzvestajService izvestajService;

    @PreAuthorize("hasRole('ROLE_OFFICIAL')")
    @PostMapping(path = "generate", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> generateReport() throws Exception {
        return new ResponseEntity("<Response>" + izvestajService.generateReport() + "</Response>", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OFFICIAL')")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getReportsIDList(Principal principal) {
        try {

            return new ResponseEntity<>(Converter.fromStringArray(izvestajService.getList()), HttpStatus.OK);
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
                        izvestajService.generatePDF(id).toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=request.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/xhtml/{id}")
    public ResponseEntity<?> getRequestHTML(@PathVariable String id) throws FileNotFoundException, UnsupportedEncodingException {
        return new ResponseEntity<>(
                izvestajService.generateHTML(id).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestRDF(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(izvestajService.getOneRDF(id).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestJSON(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(izvestajService.getOneJSON(id).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OFFICIAL')")
    @GetMapping(path = "/simple-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> simpleSearch(@RequestParam String query) {
        if (query == null || query.trim().isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);

        ResourcesListDTO resources = izvestajService.searchText(query);

        if (resources == null)
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity(resources, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OFFICIAL')")
    @PostMapping(path = "/advance-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> advanceSearch(@RequestBody ReportsAdvanceSearchQuery query) throws UnsupportedEncodingException {
        if (query.numberOfDeclinedRegex.isEmpty() && query.numberOfSubmittedRegex.isEmpty() && query.dateRegex.isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(izvestajService.queryRDF(query).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

}
