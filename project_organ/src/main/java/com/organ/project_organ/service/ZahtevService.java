package com.organ.project_organ.service;

import com.itextpdf.text.DocumentException;
import com.organ.project_organ.model.xml_opste.TTrazilac;
import com.organ.project_organ.model.xml_zahtev.*;
import com.organ.project_organ.model.xml_zahtev.ObjectFactory;
import com.organ.project_organ.pojo.*;
import com.organ.project_organ.repository.impl.ZahtevRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import com.organ.project_organ.model.xml_opste.*;
import org.xmldb.api.base.XMLDBException;

@Service
public class ZahtevService extends AbsService {
    @Autowired
    public ZahtevRepository zahtevRepository;

    public ZahtevService() {
        //Repository repository, String xslPath, String fontPath
        super("src/main/resources/zahtev_temp.xsl","src/main/resources/FreeSans.ttf");
    }

    public void create(ZahtevDokumentDTO zahtev) throws Exception {
        //TODO check
        //TODO update for GRAPH
        ObjectFactory factory = new ObjectFactory();

        Zahtev newZahtev = factory.createZahtev();

        TOrgan organ = new TOrgan();

        TAdresa adresa = new TAdresa();
        adresa.setBroj(BigInteger.valueOf(Long.valueOf(zahtev.organ.adresa.broj)));

        TAdresa.Drzava drzava = new TAdresa.Drzava();
        drzava.setProperty("pred:drzavaOrgana");
        drzava.setValue(zahtev.organ.adresa.drzava);
        adresa.setDrzava(drzava);

        TAdresa.Mesto mesto = new TAdresa.Mesto();
        mesto.setProperty("pred:mestoOrgana");
        mesto.setValue(zahtev.organ.adresa.mesto);
        adresa.setMesto(mesto);
        adresa.setPostanskiBroj(BigInteger.valueOf(Long.valueOf(zahtev.organ.adresa.postanskiBroj)));
        adresa.setUlica(zahtev.organ.adresa.ulica);

        organ.setAdresa(adresa);

        TOrgan.Naziv naziv = new TOrgan.Naziv();
        naziv.setProperty("pred:upucujeSe");
        naziv.setValue(zahtev.organ.naziv);
        organ.setNaziv(naziv);

        newZahtev.setOrgan(organ);

        TTeloZahteva telo = new TTeloZahteva();

        TParagraf paragraf = new TParagraf();

        TTipoviZahteva tipovi = new TTipoviZahteva();
        TTipZahteva tip1 = new TTipZahteva();
        TTipZahteva tip2 = new TTipZahteva();
        TTipZahteva tip3 = new TTipZahteva();
        TTipZahteva tip4 = new TTipZahteva();

        if (zahtev.zahtevam.opcija.size() != 4)
            throw new Exception("Broj opcija ne odgovara originalnom dokumentu.");

        //TODO check multiple selection
        //TODO add fields for

        tip1.getContent().add("obaveštenje da li poseduje traženu informaciju;");
        tip1.setOdabrano(zahtev.zahtevam.opcija.get(0).cekiran);
        tip2.getContent().add("uvid u dokument koji sadrži traženu informaciju;");
        tip2.setOdabrano(zahtev.zahtevam.opcija.get(1).cekiran);
        tip3.getContent().add("kopiju dokumenta koji sadrži traženu informaciju;");
        tip3.setOdabrano(zahtev.zahtevam.opcija.get(2).cekiran);
        tip4.getContent().add("dostavljanje kopije dokumenta koji sadrži traženu informaciju;");
        tip4.setOdabrano(zahtev.zahtevam.opcija.get(3).cekiran);

        TTipoviDostave tipoviDostave = new TTipoviDostave();

        TTipDostave dostava1 = new TTipDostave();
        TTipDostave dostava2 = new TTipDostave();
        TTipDostave dostava3 = new TTipDostave();
        TTipDostave dostava4 = new TTipDostave();

        dostava1.getContent().add("poštom");
        dostava2.getContent().add("elektronskom poštom");
        dostava3.getContent().add("faksom");
        dostava4.getContent().add("na drugi način:");

        // CUSTOM DELIVERY LAST OPTION!!
        List<DostavaDTO> dostavaDTOS = zahtev.zahtevam.opcija.get(zahtev.zahtevam.opcija.size() - 1).dostava;
        String dostavaNaDrugiNacin = dostavaDTOS.get(dostavaDTOS.size() - 1).dodatno;
        JAXBElement<String> dodatniTip = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Dodatni_tip"), String.class, dostavaNaDrugiNacin);
        dostava4.getContent().add(dodatniTip);

        tipoviDostave.getTipDostave().add(dostava1);
        tipoviDostave.getTipDostave().add(dostava2);
        tipoviDostave.getTipDostave().add(dostava3);
        tipoviDostave.getTipDostave().add(dostava4);

        // check delivery
        for (int i = 0; i < dostavaDTOS.size(); i++) {
            tipoviDostave.getTipDostave().get(i).setOdabrano(dostavaDTOS.get(i).cekiran);
        }

        JAXBElement<TTipoviDostave> jaxbTTipovi = new JAXBElement<TTipoviDostave>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_dostave"), TTipoviDostave.class, tipoviDostave);
        tip4.getContent().add(jaxbTTipovi);

        tipovi.getTipZahteva().add(tip1);
        tipovi.getTipZahteva().add(tip2);
        tipovi.getTipZahteva().add(tip3);
        tipovi.getTipZahteva().add(tip4);

        paragraf.getContent().add("Na osnovu člana 15. st. 1. Zakona o slobodnom pristupu informacijama od javnog značaja\n" +
                "         („Službeni glasnik RS“, br. 120/04, 54/07, 104/09 i 36/10), od gore navedenog organa\n" +
                "         zahtevam:");
        JAXBElement<TTipoviZahteva> jaxbTipovi = new JAXBElement<TTipoviZahteva>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_zahteva"), TTipoviZahteva.class, tipovi);
        paragraf.getContent().add(jaxbTipovi);

        telo.setParagraf(paragraf);

        TInformacije informacije = new TInformacije();
        informacije.setNaslov("Ovaj zahtev se odnosi na sledeće informacije:");
        informacije.setOpis(zahtev.zahtevam.opis.tekst);
        informacije.setSavet("navesti što precizniji opis informacije koja se traži kao i druge podatke koji olakšavaju pronalaženje tražene informacije");

        telo.setInformacije(informacije);

        // set telo
        newZahtev.setTeloZahteva(telo);

        // set naziv
        newZahtev.setNaziv("za pristup informaciji od javnog značaja");

        TDodatneInformacije dodatne = new TDodatneInformacije();
        TDatum datum = new TDatum();

        datum.setValue("[ovde ce se generisati vrednost]");
        datum.setProperty("pred:podnosenje");

        TOsoba osoba = new TOsoba();
        osoba.setIme("[Protoime]");
        osoba.setPrezime("[Protoprezime]");

        TAdresa tAdresa = new TAdresa();
        //todo: pass in form
        tAdresa.setDrzava(drzava);
        tAdresa.setMesto(mesto);
        tAdresa.setBroj(BigInteger.valueOf(7L));
        tAdresa.setPostanskiBroj(BigInteger.valueOf(7L));
        tAdresa.setUlica("Protoulica");

        TTrazilac trazilac = new TTrazilac();
        trazilac.setAdresa(tAdresa);
        trazilac.setKontakt("+000 0000 000");
        trazilac.setOsoba(osoba);

        dodatne.setDatum(datum);
        //todo: mesto podnosenja property
        dodatne.setMesto(zahtev.mestoPodnosenja.naziv);
        dodatne.setTrazilac(trazilac);

        // set dodatne
        newZahtev.setDodatneInformacije(dodatne);

        // id setup
        String id = UUID.randomUUID().toString();
        newZahtev.setAbout("http://localhost:8081/request/" + id);
        trazilac.setRel("pred:potrazuje");
        trazilac.setHref("http://localhost:8081/user/USER_ID");

        zahtevRepository.save(id, newZahtev);
    }

    public String[] getList() throws XMLDBException, IllegalAccessException, InstantiationException {
        return zahtevRepository.listResources();
    }

    public StringWriter generateHTML(String id) throws FileNotFoundException {
        return this.generateHTML(id, zahtevRepository);
    }

    public ByteArrayOutputStream generatePDF(String id) throws IOException, DocumentException {
        return this.generatePDF(id, zahtevRepository);
    }


    @Override
    public ByteArrayOutputStream getOneRDF(String id) throws Exception {
        return zahtevRepository.getOneMetadataRDF(id);
    }

    public ByteArrayOutputStream getOneJSON(String id) throws Exception {
        return zahtevRepository.getOneMetadataJSON(id);
    }
}