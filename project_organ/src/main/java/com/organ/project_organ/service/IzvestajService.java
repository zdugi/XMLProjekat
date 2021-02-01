package com.organ.project_organ.service;

import com.itextpdf.text.DocumentException;
import com.organ.project_organ.model.xml_izvestaj.Izvestaj;
import com.organ.project_organ.model.xml_opste.TDatum;
import com.organ.project_organ.repository.impl.IzvestajRepository;
import com.organ.project_organ.repository.impl.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        odbijeniZahtevi.setValue(BigInteger.valueOf(1));
        izvestaj.setOdbijeniZahtevi(odbijeniZahtevi);
        odbijeniZahtevi.setProperty("pred:odbijeniZahtevi");

        String id = UUID.randomUUID().toString();
        izvestaj.setAbout("http://localhost:8081/report/" + id);

        izvestajRepository.save(id, izvestaj);

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
}
