package com.xmlproject.project_poverenik.service;

import com.xmlproject.project_poverenik.model.xml_opste.*;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ObjectFactory;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.TTeloZalbeOdluka;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.xmlproject.project_poverenik.repository.ZalbaNaOdlukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.ZalbaNaOdlukuDTO;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.UUID;

@Service
public class ZalbaNaOdlukuService {

    @Autowired
    private ZalbaNaOdlukuRepository zalbaNaOdlukuRepository;

    public void create (ZalbaNaOdlukuDTO zalbaNaOdlukuDTO) throws Exception {

        ObjectFactory factory = new ObjectFactory();

        ZalbaNaOdluku zalbaNaOdluku = factory.createZalbaNaOdluku();

        zalbaNaOdluku.setPrimalac("Повереник за иинформације од јавног значаја и заштиту података о личности");

        TAdresa adresa = new TAdresa();
        adresa.setBroj(BigInteger.valueOf(Long.valueOf(15000)));

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

        zalbaNaOdluku.setAdresaPrimaoca(adresa);

        TTeloZalbeOdluka teloZalbeOdluka = new TTeloZalbeOdluka();
        teloZalbeOdluka.getContent().add("ЖАЛБА");

        TOsoba zalilac = new TOsoba();
        zalilac.setIme(zalbaNaOdlukuDTO.zalilac.ime);
        zalilac.setPrezime(zalbaNaOdlukuDTO.zalilac.prezime);

        JAXBElement<TOsoba> zalilacJ = new JAXBElement<TOsoba>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "ZalilacOsoba"), TOsoba.class, zalilac);
        teloZalbeOdluka.getContent().add(zalilacJ);

        // zalilac osoba ili zalilac organ, neka zasad bude osoba
        TAdresa adresa1 = new TAdresa();
        adresa1.setBroj(BigInteger.valueOf(Long.valueOf(15000)));

        TAdresa.Drzava drzava1 = new TAdresa.Drzava();
        drzava1.setProperty("pred:drzavaOrgana1");
        drzava1.setValue("Србија");
        adresa1.setDrzava(drzava1);

        TAdresa.Mesto mesto1 = new TAdresa.Mesto();
        mesto1.setProperty("pred:mestoOrgana1");
        mesto1.setValue("Београд");
        adresa1.setMesto(mesto);
        adresa1.setPostanskiBroj(BigInteger.valueOf(11000L));
        adresa1.setUlica("Булевар краља Александра");
        adresa1.setBroj(BigInteger.valueOf(15L));

        JAXBElement<TAdresa> adresa1J = new JAXBElement<TAdresa>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "Adresa"), TAdresa.class, adresa1);
        teloZalbeOdluka.getContent().add(adresa1J);

        teloZalbeOdluka.getContent().add("protiv resenja-zakljucka");


        TOrgan organDonosilacOdluke = new TOrgan();
        TOrgan.Naziv naziv = new TOrgan.Naziv();
        naziv.setProperty("pred:upucujeSe");
        naziv.setValue(zalbaNaOdlukuDTO.organNaKogaSeZali.naziv);
        organDonosilacOdluke.setAdresa(adresa1);    // na primjer, treba izvuci

        JAXBElement<TOrgan> organDonosilacOdluke1 = new JAXBElement<TOrgan>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "OrganDonosilacOdluke"), TOrgan.class, organDonosilacOdluke);

        teloZalbeOdluka.getContent().add(organDonosilacOdluke1);

        TIdResenja idResenja = new TIdResenja();
        idResenja.setBroj(zalbaNaOdlukuDTO.brojResenja.broj);
        //idResenja.setGodina(BigInteger.valueOf(Long.getLong(zalbaNaOdlukuDTO.brojResenja.godina)));
        idResenja.setGodina(BigInteger.valueOf(12123132132L));
        JAXBElement<TIdResenja> idResenjaJ = new JAXBElement<TIdResenja>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "BrojResenja"), TIdResenja.class, idResenja);

        teloZalbeOdluka.getContent().add(idResenjaJ);
        teloZalbeOdluka.getContent().add("Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана");

        TDatum datumPodnosenjaZahteva = new TDatum();
        datumPodnosenjaZahteva.setValue("neki datum");
        JAXBElement<TDatum> datumPodnosenjaZahtevaJ = new JAXBElement<TDatum>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "Datum_podnosenja_zahteva"), TDatum.class, datumPodnosenjaZahteva);

        teloZalbeOdluka.getContent().add(datumPodnosenjaZahtevaJ);

        teloZalbeOdluka.getContent().add("године и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим");
        JAXBElement<String> opisZalbeJ = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "OpisZalbe"), String.class, zalbaNaOdlukuDTO.opisZalbe);
        teloZalbeOdluka.getContent().add(opisZalbeJ);

        teloZalbeOdluka.getContent().add("јер није заснована на Закону о слободном приступу информацијама од јавног значаја.\n" +
                "На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.\n" +
                "Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1. Закона о слободном приступу информацијама од јавног значаја.\n");


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
        mestoDI.setValue(zalbaNaOdlukuDTO.dodatneInformacije.mesto);
        tAdresa.setMesto(mestoDI);

        com.xmlproject.project_poverenik.model.xml_opste.TTrazilac trazilac = new TTrazilac();
        trazilac.setAdresa(tAdresa);
        trazilac.setKontakt("[uzimam iz sesije]");
        trazilac.setOsoba(osoba);

        tDodatneInformacije.setTrazilac(trazilac);

        zalbaNaOdluku.setTeloZalbeNaOdluku(teloZalbeOdluka);

        zalbaNaOdluku.setDodatneInformacije(tDodatneInformacije);

        //zalbaNaOdluku.setNapomene();

        String id = UUID.randomUUID().toString();
        zalbaNaOdluku.setAbout("http://localhost:8081/complaint/resolution/" + id);



        zalbaNaOdlukuRepository.save(zalbaNaOdluku);
    }

    public ZalbaNaOdluku getOne (String id) throws Exception {
        return zalbaNaOdlukuRepository.getOne(id);
    }

}
