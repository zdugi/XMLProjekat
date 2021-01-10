package com.xmlproject.project_poverenik.controller;

import com.xmlproject.project_poverenik.model.Customer;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaresenje.ZalbaNaResenje;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaint")
public class ZalbaController {

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public String resolve(@RequestBody ZalbaNaCutanje zalbaNaCutanje) {
        return "I'm ok. Status: " + zalbaNaCutanje.getNaziv();
    }
}
