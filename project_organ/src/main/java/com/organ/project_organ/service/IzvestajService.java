package com.organ.project_organ.service;

import com.itextpdf.text.DocumentException;
import com.organ.project_organ.model.xml_izvestaj.Izvestaj;
import com.organ.project_organ.model.xml_opste.TDatum;
import com.organ.project_organ.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.organ.project_organ.pojo.ReportsAdvanceSearchQuery;
import com.organ.project_organ.pojo.RequestsAdvanceSearchQuery;
import com.organ.project_organ.pojo.ResourcesListDTO;
import com.organ.project_organ.repository.impl.IzvestajRepository;
import com.organ.project_organ.repository.impl.OdbijeniZahteviRepository;
import com.organ.project_organ.repository.impl.ZahtevRepository;
import com.organ.project_organ.ws.izvestaj.IzvestajInterface;
import com.organ.project_organ.ws.zalba.ZalbaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.*;
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

    @Autowired
    private OdbijeniZahteviService odbijeniZahteviService;

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

        URL wsdl = new URL("http://localhost:8081/ws/zalba?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/zalba", "ZalbaService");
        QName portName = new QName("http://soap.spring.com/ws/zalba", "ZalbaPort");

        javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdl, serviceName);

        ZalbaInterface address = service.getPort(portName, ZalbaInterface.class);
        //kreiranje objekta

        if (address.getZalbe() != null)
            for (ZalbaNaOdluku zalba : address.getZalbe())
                if (zalba.getStatus() != null && zalba.getStatus().getValue().equals("одбијена")) {
                    // extraction
                    for (Serializable s : zalba.getTeloZalbeNaOdluku().getContent()) {
                        try {
                            if (s instanceof String)
                                continue;
                            JAXBElement<?> ser = (JAXBElement<?>) s;
                            String tagFullName = ser.getName().toString();
                            String tagName = tagFullName.substring(tagFullName.indexOf("}") + 1);
                            if (tagName.equals("OpisZalbe")) {
                                ls.getSadrzinaOdbijeneZalbe().add("[ID: " + zalba.getId() + "] " + ser.getValue());
                                break;
                            } else {
                                continue;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

        izvestaj.setSadrzinaOdbijenihZalbi(ls);

        Izvestaj.PodnetiZahtevi podnetiZahtevi = new Izvestaj.PodnetiZahtevi();
        //TODO fix
        podnetiZahtevi.setValue(BigInteger.valueOf(zahtevRepository.listResources().length));
        izvestaj.setPodnetiZahtevi(podnetiZahtevi);
        podnetiZahtevi.setProperty("pred:brojPod");

        Izvestaj.OdbijeniZahtevi odbijeniZahtevi = new Izvestaj.OdbijeniZahtevi();
        odbijeniZahtevi.setValue(BigInteger.valueOf(odbijeniZahteviService.getNumberOfDeclined()));
        izvestaj.setOdbijeniZahtevi(odbijeniZahtevi);
        odbijeniZahtevi.setProperty("pred:odbijeniZahtevi");

        String id = UUID.randomUUID().toString();
        izvestaj.setAbout("http://localhost:8081/report/" + id);

        // write to db
        izvestajRepository.save(id, izvestaj);

        // send report
        try {
            wsdl = new URL("http://localhost:8081/ws/report?wsdl");
            serviceName = new QName("http://soap.spring.com/ws/report", "IzvestajService");
            portName = new QName("http://soap.spring.com/ws/report", "IzvestajPort");

            service = javax.xml.ws.Service.create(wsdl, serviceName);

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
        String sparqlQuery = "SELECT * FROM <http://localhost:8080/fusekiOrgan/EDataset2/data/example/izvestaj/metadata>\n" +
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
