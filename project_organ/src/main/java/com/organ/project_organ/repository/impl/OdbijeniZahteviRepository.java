package com.organ.project_organ.repository.impl;

import com.organ.project_organ.model.odbijeni_zahtevi.OdbijeniZahtevi;
import com.organ.project_organ.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class OdbijeniZahteviRepository extends Repository<OdbijeniZahtevi> {
    public OdbijeniZahteviRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
}
