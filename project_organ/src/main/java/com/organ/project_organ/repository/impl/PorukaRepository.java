package com.organ.project_organ.repository.impl;

import com.organ.project_organ.model.poruka.Poruka;
import com.organ.project_organ.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class PorukaRepository extends Repository<Poruka> {
    public PorukaRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
}
