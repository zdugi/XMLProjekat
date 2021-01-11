package com.xmlproject.project_poverenik.controller;

import com.xmlproject.project_poverenik.model.xml_resenje.Resenje;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.repository.ResenjeRepository;
import com.xmlproject.project_poverenik.service.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solution")
public class ResenjeController {
    @Autowired
    private ResenjeService resenjeService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createResenje(@RequestBody Resenje resenje) {
        try {
            resenjeService.create(resenje);
            return new ResponseEntity<>("<Response><Status>Created</Status></Response>", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>("<Response><Status>Error</Status></Response>", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalba(@PathVariable String id) {
        Resenje resenje = null;
        try {
            resenje = resenjeService.getOne(id);
            return new ResponseEntity<>(resenje, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(resenje, HttpStatus.BAD_REQUEST);
        }

    }

}
