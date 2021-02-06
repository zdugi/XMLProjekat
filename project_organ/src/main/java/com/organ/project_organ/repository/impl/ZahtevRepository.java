package com.organ.project_organ.repository.impl;

import com.organ.project_organ.model.xml_zahtev.Zahtev;
import com.organ.project_organ.repository.Repository;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;

@Component
public class ZahtevRepository extends Repository<Zahtev> {

    public ZahtevRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }

    public String[] getRequestsListForUser(String userId) {
        String sparqlQuery = "SELECT * FROM <http://localhost:8080/fusekiOrgan/EDataset2/data/example/zahtev/metadata> " +
                "WHERE {\n" +
                "  ?s <http://localhost/predikati/potrazuje> <http://ftn.uns.ac.rs/user/%s>\n" +
                "}\n" +
                "LIMIT 100";
        sparqlQuery = String.format(sparqlQuery, userId);

        QueryExecution query = QueryExecutionFactory.sparqlService(queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();

        String varName;
        RDFNode varValue;

        ArrayList<String> ids = new ArrayList();

        while(results.hasNext()) {

            // A single answer from a SELECT query
            QuerySolution querySolution = results.next() ;
            Iterator<String> variableBindings = querySolution.varNames();

            // Retrieve variable bindings
            while (variableBindings.hasNext()) {

                varName = variableBindings.next();
                varValue = querySolution.get(varName);

                String[] parts = varValue.toString().split("/");
                ids.add(parts[parts.length - 1]);
            }
        }

        String[] ret = new String[ids.size()];
        ids.toArray(ret);

        return ret;
    }
}
