package com.organ.project_organ.controller;

import com.organ.project_organ.pojo.ObavestenjeDTO;
import com.organ.project_organ.service.ObavestenjeService;
import com.organ.project_organ.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

@RestController
@RequestMapping("/api/notification")
public class ObavestenjeController {

    @Autowired
    private ObavestenjeService obavestenjeService;

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

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createRequest(@RequestBody ObavestenjeDTO obavestenjeDTO) {
        try {
            obavestenjeService.create(obavestenjeDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("<Status>Error</Status>", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("<Status>OK</Status>", HttpStatus.OK);
    }

}
