package com.xmlproject.project_poverenik.controller;

import com.xmlproject.project_poverenik.model.Customer;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public String resolve(@RequestBody Customer zalbaNaCutanje) {
        return "I'm ok. Status: " + zalbaNaCutanje.getName();
    }
}
