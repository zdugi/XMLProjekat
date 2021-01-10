package com.xmlproject.project_poverenik.controller;

import com.xmlproject.project_poverenik.model.Customer;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaresenje.ZalbaNaResenje;
import com.xmlproject.project_poverenik.service.ZalbaNaCutanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaint")
public class ZalbaController {

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public String resolve(@RequestBody ZalbaNaCutanje zalbaNaCutanje) {
        try {
            zalbaNaCutanjeService.create(zalbaNaCutanje);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "I'm ok. Status: " + zalbaNaCutanje.getNaziv();
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
}