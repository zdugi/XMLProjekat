package com.organ.project_organ.repository.impl;


import com.organ.project_organ.model.xml_izvestaj.Izvestaj;
import com.organ.project_organ.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class IzvestajRepository extends Repository<Izvestaj> {
    public IzvestajRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
}
