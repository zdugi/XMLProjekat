package com.organ.project_organ.repository.impl;

import com.organ.project_organ.model.xml_zahtev.Zahtev;
import com.organ.project_organ.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class ZahtevRepository extends Repository<Zahtev> {

    public ZahtevRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
}
