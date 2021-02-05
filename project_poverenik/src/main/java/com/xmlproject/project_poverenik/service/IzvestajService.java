package com.xmlproject.project_poverenik.service;

import com.itextpdf.text.DocumentException;

import com.xmlproject.project_poverenik.repository.IzvestajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import pojo.ComplaintsListDTO;
import pojo.ReportsAdvanceSearchQuery;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class IzvestajService extends AbsService {
    @Autowired
    private IzvestajRepository izvestajRepository;


    public IzvestajService() {
        super("src/main/resources/izvestaj_temp.xsl","src/main/resources/FreeSans.ttf");
    }


    @Override
    public ByteArrayOutputStream getOneRDF(String id) throws Exception {
        return izvestajRepository.getOneMetadataRDF(id);
    }

    @Override
    public ByteArrayOutputStream getOneJSON(String id) throws Exception {
        return izvestajRepository.getOneMetadataJSON(id);
    }

    public StringWriter generateHTML(String id) throws FileNotFoundException {
        return this.generateHTML(id, izvestajRepository);
    }

    public ByteArrayOutputStream generatePDF(String id) throws IOException, DocumentException {
        return this.generatePDF(id, izvestajRepository);
    }

    public String[] getList() throws XMLDBException, IllegalAccessException, InstantiationException {
        return izvestajRepository.listResources();
    }

    public ComplaintsListDTO searchText(String query) {
        try {
            return izvestajRepository.searchText(query);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (XMLDBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ByteArrayOutputStream queryRDF(ReportsAdvanceSearchQuery query) {
        String sparqlQuery = "SELECT * FROM <http://localhost:8080/fuseki/EDataset/data/example/izvestaj/metadata>\n" +
                "WHERE {\n" +
                "  ?subject <http://localhost/predikati/brojPod> ?brojPodnetih .\n" +
                "  ?subject <http://localhost/predikati/odbijeniZahtevi> ?brojOdbijenihZahteva .\n" +
                "  ?subject <http://localhost/predikati/podnet> ?podnet .\n" +
                "  FILTER (regex(str(?brojPodnetih), \"%s\")) .\n" +
                "  FILTER (regex(str(?brojOdbijenihZahteva), \"%s\"))\n" +
                "  FILTER (regex(str(?podnet), \"%s\"))\n" +
                "}\n" +
                "LIMIT 100\n";

        // NO ESCAPE!
        sparqlQuery = String.format(
                sparqlQuery,
                query.numberOfSubmittedRegex,
                query.numberOfDeclinedRegex,
                query.dateRegex
        );

        return izvestajRepository.queryRDF(sparqlQuery);
    }
}
