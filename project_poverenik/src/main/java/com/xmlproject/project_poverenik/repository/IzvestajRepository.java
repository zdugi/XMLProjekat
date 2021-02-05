package com.xmlproject.project_poverenik.repository;


import com.xmlproject.project_poverenik.model.xml_izvestaj.Izvestaj;
import org.springframework.stereotype.Component;

@Component
public class IzvestajRepository extends Repository<Izvestaj> {
    public IzvestajRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
}
