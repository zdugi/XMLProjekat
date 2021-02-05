package com.xmlproject.project_poverenik.repository;

import com.xmlproject.project_poverenik.model.poruka.Poruka;
import org.exist.xmldb.DatabaseImpl;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;
import pojo.ComplaintsListDTO;

import java.util.ArrayList;

@Component
public class PorukaRepository extends Repository<Poruka> {

    @Value("${conn.uri}")
    private String connUri;

    public PorukaRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }
    public String findByComplaint(String queryStr) throws IllegalAccessException, InstantiationException, XMLDBException {
        ComplaintsListDTO resourcesListDTO = new ComplaintsListDTO();
        resourcesListDTO.complaint = new ArrayList<>();

        String id = "";

        // initialize database driver
        Class<?> cl = DatabaseImpl.class;

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        Collection col = null;

        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $poruka in collection(\"/db/sample/poruka/\")\n" +
                "where fn:contains(lower-case($poruka), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($poruka), \"poruka/\")";

        try {
            col = DatabaseManager.getCollection(connUri + "/db/sample/poruka");

            // get an instance of xquery service
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            // compile and execute the expression
            CompiledExpression compiledXquery = xqueryService.compile(String.format(textContainQuery, queryStr));
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            Resource res = null;

            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    id = res.getContent().toString();

                } finally {

                    // don't forget to cleanup resources
                    try {
                        ((EXistResource) res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } finally {
            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return id;
    }

}
