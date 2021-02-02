package com.organ.project_organ.repository.impl;

import com.organ.project_organ.model.xml_obavestenja.Obavestenje;
import com.organ.project_organ.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class ObavestenjeRepository extends Repository<Obavestenje> {

    public ObavestenjeRepository(String graphURI, String collectionId, String instancePath) {
        super(graphURI, collectionId, instancePath);
    }
}
