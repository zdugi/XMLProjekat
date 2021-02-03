package com.organ.project_organ.service;

import com.itextpdf.text.DocumentException;
import com.organ.project_organ.model.xml_izvestaj.Izvestaj;
import com.organ.project_organ.model.xml_opste.TDatum;
import com.organ.project_organ.pojo.ReportsAdvanceSearchQuery;
import com.organ.project_organ.pojo.RequestsAdvanceSearchQuery;
import com.organ.project_organ.pojo.ResourcesListDTO;
import com.organ.project_organ.repository.impl.IzvestajRepository;
import com.organ.project_organ.repository.impl.ZahtevRepository;
import com.organ.project_organ.ws.izvestaj.IzvestajInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class IzvestajService extends AbsService {
    @Autowired
    private IzvestajRepository izvestajRepository;

    @Autowired
    private ZahtevRepository zahtevRepository;

    public IzvestajService() {
        super("src/main/resources/izvestaj_temp.xsl","src/main/resources/FreeSans.ttf");
    }

    public String generateReport() throws Exception {
        Izvestaj izvestaj = new Izvestaj();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        TDatum datum = new TDatum();
        datum.setValue(formatter.format(date));
        datum.setProperty("pred:podnet");

        izvestaj.setDatumPodnosenja(datum);

        Izvestaj.SadrzinaOdbijenihZalbi ls = new Izvestaj.SadrzinaOdbijenihZalbi();
        ls.getSadrzinaOdbijeneZalbe().add("Zalba 1");
        ls.getSadrzinaOdbijeneZalbe().add("Zalba 2");
        ls.getSadrzinaOdbijeneZalbe().add("Zalba 3");
        ls.getSadrzinaOdbijeneZalbe().add("Zalba 4");

        izvestaj.setSadrzinaOdbijenihZalbi(ls);

        Izvestaj.PodnetiZahtevi podnetiZahtevi = new Izvestaj.PodnetiZahtevi();
        //TODO fix
        podnetiZahtevi.setValue(BigInteger.valueOf(/*zahtevRepository.listResources().length*/666));
        izvestaj.setPodnetiZahtevi(podnetiZahtevi);
        podnetiZahtevi.setProperty("pred:brojPod");

        Izvestaj.OdbijeniZahtevi odbijeniZahtevi = new Izvestaj.OdbijeniZahtevi();
        //TODO fix
        Random rnd = new Random();
        odbijeniZahtevi.setValue(BigInteger.valueOf(rnd.nextInt() % 100000));
        izvestaj.setOdbijeniZahtevi(odbijeniZahtevi);
        odbijeniZahtevi.setProperty("pred:odbijeniZahtevi");

        String id = UUID.randomUUID().toString();
        izvestaj.setAbout("http://localhost:8081/report/" + id);

        // write to db
        izvestajRepository.save(id, izvestaj);

        // send report
        try {
            URL wsdlLocation = new URL("http://localhost:8081/ws/report?wsdl");
            QName serviceName = new QName("http://soap.spring.com/ws/report", "IzvestajService");
            QName portName = new QName("http://soap.spring.com/ws/report", "IzvestajPort");

            javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);

            IzvestajInterface izvestaji = service.getPort(portName, IzvestajInterface.class);
            izvestaji.sendReport(izvestaj);

        } catch (Exception e) {
            System.err.println("Dosle je do greske prilikom slanja izvestaja putem WS.");
        }

        return id;
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

    public ResourcesListDTO searchText(String query) {
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

        return zahtevRepository.queryRDF(sparqlQuery);
    }
}
