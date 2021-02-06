package com.organ.project_organ.repository.impl;

import com.organ.project_organ.model.xml_resenje.Resenje;
import com.organ.project_organ.repository.Repository;

public class ResenjeRepository extends Repository<Resenje> {
    public ResenjeRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
}
