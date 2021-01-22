package com.xmlproject.project_poverenik.repository.impl;

import com.xmlproject.project_poverenik.model.xml_zahtev.Zahtev;
import com.xmlproject.project_poverenik.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class ZahtevRepository extends Repository<Zahtev> {

    public ZahtevRepository(String graphURI, String collectionId, String instancePath) {
        super(graphURI, collectionId, instancePath);
    }
}
