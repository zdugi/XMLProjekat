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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.print.attribute.standard.Media;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import java.io.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/requests")
public class ZahtevController {
    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private OdbijeniZahteviService odbijeniZahteviService;

    @PreAuthorize("hasRole('ROLE_CITIZEN')")
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createRequest(@RequestBody ZahtevDokumentDTO zahtevDokumentDTO, Principal principal) {
        try {
            zahtevService.create(zahtevDokumentDTO, principal.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("<Status>OK</Status>", HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestsIDList(Principal principal) {
        try {
            return new ResponseEntity<>(Converter.fromStringArray(zahtevService.getRequestsIDList(principal.getName())), HttpStatus.OK);
        } catch (Exception e) {
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
    public ResponseEntity<?> getRequestHTML(@PathVariable String id) throws FileNotFoundException, UnsupportedEncodingException {
        return new ResponseEntity<>(
                zahtevService.generateHTML(id).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRequestRDF(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zahtevService.getOneRDF(id).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestJSON(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(zahtevService.getOneJSON(id).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OFFICIAL')")
    @GetMapping(path = "/simple-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> simpleSearch(@RequestParam String query) {
        if (query == null || query.trim().isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);

        ResourcesListDTO resources = zahtevService.searchText(query);

        if (resources == null)
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity(resources, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OFFICIAL')")
    @PostMapping(path = "/advance-search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> advanceSearch(@RequestBody RequestsAdvanceSearchQuery query) throws UnsupportedEncodingException {
        if (query.applicantRegex.isEmpty() && query.submissionDateRegex.isEmpty() &&
            query.authorityRegex.isEmpty() && query.placeRegex.isEmpty() && query.stateRegex.isEmpty())
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(zahtevService.queryRDF(query).toString().getBytes("UTF-8"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OFFICIAL')")
    @PostMapping(path = "/decline/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> decline(@PathVariable String id) throws UnsupportedEncodingException {
        System.out.print(id);
        if(odbijeniZahteviService.declineRequest(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
