package com.organ.project_organ.controller;

import com.organ.project_organ.pojo.*;
import com.organ.project_organ.service.*;
import com.organ.project_organ.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

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
}
