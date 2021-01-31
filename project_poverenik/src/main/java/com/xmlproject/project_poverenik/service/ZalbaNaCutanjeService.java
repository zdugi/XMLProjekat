package com.xmlproject.project_poverenik.service;

import com.xmlproject.project_poverenik.model.xml_opste.*;
import com.xmlproject.project_poverenik.model.xml_opste.TTrazilac;
import com.xmlproject.project_poverenik.model.xml_zahtev.*;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ObjectFactory;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.TRazloziZalbe;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.TTeloZalbe;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.repository.ZalbaNaCutanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.DostavaDTO;
import pojo.ZalbaNaCutanjeDTO;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Service
public class ZalbaNaCutanjeService {

    @Autowired
    private ZalbaNaCutanjeRepository zalbaNaCutanjeRepository;

    public void create (ZalbaNaCutanjeDTO zalbaNaCutanje) throws Exception {
        ObjectFactory factory = new ObjectFactory();

        ZalbaNaCutanje newZalba = factory.createZalbaNaCutanje();

        newZalba.setPrimalac("Повереник за иинформације од јавног значаја и заштиту података о личности");

        TAdresa adresa = new TAdresa();
        adresa.setBroj(BigInteger.valueOf(Long.valueOf(zalbaNaCutanje.organ.adresa.broj)));

        TAdresa.Drzava drzava = new TAdresa.Drzava();
        drzava.setProperty("pred:drzavaOrgana");
        drzava.setValue("Србија");
        adresa.setDrzava(drzava);

        TAdresa.Mesto mesto = new TAdresa.Mesto();
        mesto.setProperty("pred:mestoOrgana");
        mesto.setValue("Београд");
        adresa.setMesto(mesto);
        adresa.setPostanskiBroj(BigInteger.valueOf(11000L));
        adresa.setUlica("Булевар краља Александра");
        adresa.setBroj(BigInteger.valueOf(15L));

        newZalba.setAdresaPrimaoca(adresa);

        TTeloZalbe tTeloZalbe = new TTeloZalbe();

        tTeloZalbe.getContent().add("У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим: " +
                "Ж А Л Б У против");

        TOrgan organ = new TOrgan();
        TOrgan.Naziv naziv = new TOrgan.Naziv();
        naziv.setProperty("pred:upucujeSe");
        naziv.setValue(zalbaNaCutanje.organ.naziv);
        organ.setNaziv(naziv);

        JAXBElement<TOrgan> organTelo = new JAXBElement<TOrgan>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Organ"), TOrgan.class, organ);
        tTeloZalbe.getContent().add(organTelo);

        TRazloziZalbe tRazloziZalbe= new TRazloziZalbe();
        TRazloziZalbe.RazlogZalbe razlogZalbe1 = new TRazloziZalbe.RazlogZalbe();
        TRazloziZalbe.RazlogZalbe razlogZalbe2 = new TRazloziZalbe.RazlogZalbe();
        TRazloziZalbe.RazlogZalbe razlogZalbe3 = new TRazloziZalbe.RazlogZalbe();

        razlogZalbe1.setValue("није поступио");
        razlogZalbe1.setOdabrano(zalbaNaCutanje.opcija.get(0).cekiran);
        razlogZalbe1.setValue("није поступио у целости");
        razlogZalbe1.setOdabrano(zalbaNaCutanje.opcija.get(1).cekiran);
        razlogZalbe1.setValue("није поступио у законском року");
        razlogZalbe1.setOdabrano(zalbaNaCutanje.opcija.get(2).cekiran);

        tRazloziZalbe.getRazlogZalbe().add(razlogZalbe1);
        tRazloziZalbe.getRazlogZalbe().add(razlogZalbe2);
        tRazloziZalbe.getRazlogZalbe().add(razlogZalbe3);

        JAXBElement<TRazloziZalbe> razloziZalbeTelo = new JAXBElement<TRazloziZalbe>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Razlozi_zalbe"), TRazloziZalbe.class, tRazloziZalbe);
        tTeloZalbe.getContent().add(razloziZalbeTelo);

        String tekst = "по мом захтеву за слободан приступ информацијама од јавног значаја који сам поднео том органу дана по мом захтеву за слободан приступ информацијама од јавног значаја који сам поднео том органу  дана ";
        JAXBElement<String> stringTelo = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje"), String.class, tekst);
        tTeloZalbe.getContent().add(stringTelo);

        TDatum datum = new TDatum();
        datum.setValue(zalbaNaCutanje.datumPodnosenja.datumPodnosenjaA);
        JAXBElement<TDatum> datumTelo = new JAXBElement<TDatum>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Datum_podnosenja_zahteva"), TDatum.class, datum);
        tTeloZalbe.getContent().add(datumTelo);

        String tekst1 = "године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са :";
        JAXBElement<String> stringTelo1 = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje"), String.class, tekst1);
        tTeloZalbe.getContent().add(stringTelo1);

        JAXBElement<String> stringTelo2 = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Podaci_o_zahtevu_i_informacija"), String.class, zalbaNaCutanje.podaciOZahtevuIInformacijama);
        tTeloZalbe.getContent().add(stringTelo2);

        String tekst2 = "(навести податке о захтеву и информацији/ама)\n" +
                "\n" +
                "На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им информацији/ма.";
        JAXBElement<String> stringTelo3 = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje"), String.class, tekst2);
        tTeloZalbe.getContent().add(stringTelo3);

        String napomena = "Напомена: Код жалбе због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.";
        JAXBElement<String> napomenaTelo = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Napomena"), String.class, napomena);
        tTeloZalbe.getContent().add(napomenaTelo);

        TDodatneInformacije tDodatneInformacije = new TDodatneInformacije();

        TDatum datumDI = new TDatum();

        datumDI.setValue("[ovde ce se generisati vrednost]");
        datumDI.setProperty("pred:podnosenje");

        TOsoba osoba = new TOsoba();
        osoba.setIme("[osoba iz sesije]");
        osoba.setPrezime("[osoba iz sesije]");

        TAdresa tAdresa = new TAdresa();
        //todo: pass in form
        TAdresa.Mesto mestoDI = new TAdresa.Mesto();
        mestoDI.setValue(zalbaNaCutanje.dodatneInformacije.mesto);
        tAdresa.setMesto(mestoDI);

        com.xmlproject.project_poverenik.model.xml_opste.TTrazilac trazilac = new TTrazilac();
        trazilac.setAdresa(tAdresa);
        trazilac.setKontakt("[uzimam iz sesije]");
        trazilac.setOsoba(osoba);

        tDodatneInformacije.setTrazilac(trazilac);

        newZalba.setDodatneInformacije(tDodatneInformacije);

        // set naziv
        newZalba.setNaziv("ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)");

        // id setup
        String id = UUID.randomUUID().toString();
        newZalba.setAbout("http://localhost:8081/complaint/" + id);
        trazilac.setRel("pred:potrazuje");
        trazilac.setHref("http://localhost:8081/complaint/" + id);

        zalbaNaCutanjeRepository.save(newZalba);
    }

    public ZalbaNaCutanje getOne (String id) throws Exception {
        return zalbaNaCutanjeRepository.getOne(id);
    }

}
