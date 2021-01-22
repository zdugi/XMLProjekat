package com.xmlproject.project_poverenik.controller;

import com.xmlproject.project_poverenik.model.xml_zahtev.Zahtev;
import com.xmlproject.project_poverenik.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Dete;
import pojo.Otac;
import pojo.ZahtevDokumentDTO;

@RestController
@RequestMapping("/api/request")
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
        }
        return new ResponseEntity<String>("<Status>OK</Status>", HttpStatus.OK);
    }
}
