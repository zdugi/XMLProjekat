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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

@RestController
@RequestMapping("/api/requests")
public class ZahtevController {
    @Autowired
    private ZahtevService zahtevService;

    @PostMapping(path="otac", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createDummy(@RequestBody Otac otac) {
        //otac.dete = new Dete();
        //otac.dete.ime = "Zdravko";
        //otac.dete.prezime = "Dugonjic";
        return new ResponseEntity<Otac>(otac, HttpStatus.OK);
    }

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
    public ResponseEntity<?> getRequests() {
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

    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPDF() throws IOException, DocumentException {
        /*
        try {
            StringWriter sw = zahtevService.generateHTML("3f4004c9-257f-463c-8b53-6db5a0e9fb49", "src/main/resources/zahtev_temp.xsl");
            zahtevService.generatePDF("src/main/resources/bookstore.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
         */
        ByteArrayInputStream bis =
                new ByteArrayInputStream(
                        zahtevService.generatePDF(
                                "3f4004c9-257f-463c-8b53-6db5a0e9fb49",
                                "src/main/resources/zahtev_temp.xsl").toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/html")
    public ResponseEntity<?> getHTML() throws FileNotFoundException {
        return new ResponseEntity<>(
                zahtevService.generateHTML(
                        "3f4004c9-257f-463c-8b53-6db5a0e9fb49",
                        "src/main/resources/zahtev_temp.xsl").toString(), HttpStatus.OK);
    }

}
