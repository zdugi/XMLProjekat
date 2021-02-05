package com.xmlproject.project_poverenik.repository;

import com.xmlproject.project_poverenik.model.poruka.Poruka;
import org.springframework.stereotype.Component;

@Component
public class PorukaRepository extends Repository<Poruka> {
    public PorukaRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
}
