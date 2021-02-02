package com.organ.project_organ.service;

import com.organ.project_organ.model.xml_obavestenja.*;
import com.organ.project_organ.model.xml_opste.TAdresa;
import com.organ.project_organ.model.xml_opste.TDatum;
import com.organ.project_organ.model.xml_opste.TOrgan;
import com.organ.project_organ.model.xml_opste.TZakon;
import com.organ.project_organ.pojo.ObavestenjeDTO;
import com.organ.project_organ.pojo.OpcijaObavestenjeDTO;
import com.organ.project_organ.pojo.OrganDTO;
import com.organ.project_organ.repository.impl.ObavestenjeRepository;
import org.apache.jena.assembler.JA;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import java.math.BigInteger;
import java.util.UUID;

@Service
public class ObavestenjeService {

    @Autowired
    public ObavestenjeRepository obavestenjeRepository;

    public ObavestenjeService(){
        documentFactory  = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();
    }

    public String[] getList() throws XMLDBException, IllegalAccessException, InstantiationException {
        return obavestenjeRepository.listResources();
    }

    public void create(ObavestenjeDTO obavestenjeDTO) throws Exception{

        ObjectFactory factory = new ObjectFactory();

        Obavestenje obavestenje = factory.createObavestenje();

        obavestenje.setNaziv("naziv");
        obavestenje.setBrojPredmeta("broj predmeta");

        TDatum datum = new TDatum();
        datum.setValue("[ovde ce se generisati vrednost]");
        datum.setProperty("pred:datumObavestenja");
        obavestenje.setDatum(datum);

        TOrgan organ = new TOrgan();

        TOrgan.Naziv naziv = new TOrgan.Naziv();
        naziv.setProperty("pred:nazivOrgana");
        naziv.setValue(obavestenjeDTO.organ.naziv);

        TAdresa adresa = new TAdresa();
        adresa.setBroj(BigInteger.valueOf(Long.valueOf(obavestenjeDTO.organ.adresa.broj)));

        TAdresa.Drzava drzava = new TAdresa.Drzava();
        drzava.setProperty("pred:drzavaOrgana");
        drzava.setValue(obavestenjeDTO.organ.adresa.drzava);
        adresa.setDrzava(drzava);

        TAdresa.Mesto mesto = new TAdresa.Mesto();
        mesto.setProperty("pred:mestoOrgana");
        mesto.setValue(obavestenjeDTO.organ.adresa.mesto);
        adresa.setMesto(mesto);
        adresa.setPostanskiBroj(BigInteger.valueOf(Long.valueOf(obavestenjeDTO.organ.adresa.postanskiBroj)));
        adresa.setUlica(obavestenjeDTO.organ.adresa.ulica);

        organ.setNaziv(naziv);
        organ.setAdresa(adresa);

        obavestenje.setOrgan(organ);

        TPodnosilac podnosilac = new TPodnosilac();

        TPodnosilac.Naziv nazivPodnosioca = new TPodnosilac.Naziv(); // moze i osoba zavisi ko trazi
        nazivPodnosioca.setProperty("pred:nazivPodnosioca");
        nazivPodnosioca.setValue("Podnosilac");
        podnosilac.setNaziv(nazivPodnosioca);

        obavestenje.setPodaciPodnosioca(podnosilac);

        TAdresa adresaPodnosioca = new TAdresa();
        adresaPodnosioca.setBroj(BigInteger.valueOf(Long.valueOf(15)));

        TAdresa.Drzava drzavaPodnosioca = new TAdresa.Drzava();
        drzavaPodnosioca.setProperty("pred:drzavaPodnosioca");
        drzavaPodnosioca.setValue("Drzava");
        adresaPodnosioca.setDrzava(drzavaPodnosioca);

        TAdresa.Mesto mestoPodnosioca = new TAdresa.Mesto();
        mestoPodnosioca.setProperty("pred:mestoPosnosioca");
        mestoPodnosioca.setValue("Mesto");
        adresaPodnosioca.setMesto(mestoPodnosioca);
        adresaPodnosioca.setPostanskiBroj(BigInteger.valueOf(21000));
        adresaPodnosioca.setUlica("Ulica");

        podnosilac.setAdresa(adresa);

        TTeloObavestenja teloObavestenja = new TTeloObavestenja();

        TZakon zakon = new TZakon();
        zakon.setNazivZakona(obavestenjeDTO.zakon.naziv);

        teloObavestenja.getContent().add(new JAXBElement<TZakon>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Zakon"), TZakon.class, zakon));

        Integer godine = Integer.parseInt(obavestenjeDTO.teloObavestenja.godina);
        teloObavestenja.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Godina"), Integer.class, godine));

        teloObavestenja.getContent().add("god., kojim ste tražili uvid u dokument/e sa informacijama o / u vezi sa:");

        TTeloObavestenja.Opis opis = new TTeloObavestenja.Opis();
        opis.setSavet("opis trazene informacije");
        opis.setValue("TRAZENA INFORMACIJA"); // iz drugog fajla

        teloObavestenja.getContent().add(new JAXBElement<TTeloObavestenja.Opis>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Opis"), TTeloObavestenja.Opis.class, opis));

        teloObavestenja.getContent().add(" obaveštavamo vas da dana ");

        Integer dan = Integer.parseInt(obavestenjeDTO.teloObavestenja.dan) ;
        teloObavestenja.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Dan"), Integer.class, dan));

        teloObavestenja.getContent().add(" , u casova ");

        Integer casovi = Integer.parseInt(obavestenjeDTO.teloObavestenja.sati) ;
        teloObavestenja.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Sati"), Integer.class, casovi));

        teloObavestenja.getContent().add(" , odnosno u vrenu od ");

        Integer casoviPocetak = Integer.parseInt(obavestenjeDTO.teloObavestenja.pocetniSati) ;
        teloObavestenja.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Pocetni_sat"), Integer.class, casoviPocetak));


        teloObavestenja.getContent().add(" do ");

        Integer casoviKraj = Integer.parseInt(obavestenjeDTO.teloObavestenja.zavrsniSati) ;
        teloObavestenja.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Zavrsni_sat"), Integer.class, casoviKraj));

        teloObavestenja.getContent().add(" casova, u prostorijama organa u ");

        TAdresa aresaOrgana = new TAdresa();
        aresaOrgana.setBroj(BigInteger.valueOf(Long.valueOf(obavestenjeDTO.teloObavestenja.adresa.broj)));

        TAdresa.Drzava drzavaOrgana = new TAdresa.Drzava();
        drzavaOrgana.setProperty("pred:drzavaOrganaObavestenje");
        drzavaOrgana.setValue(obavestenjeDTO.teloObavestenja.adresa.drzava);
        aresaOrgana.setDrzava(drzavaPodnosioca);

        TAdresa.Mesto mestoOrgana = new TAdresa.Mesto();
        mestoOrgana.setProperty("pred:mestoOrganaObavestenje");
        mestoOrgana.setValue(obavestenjeDTO.teloObavestenja.adresa.mesto);
        aresaOrgana.setMesto(mestoOrgana);
        aresaOrgana.setPostanskiBroj(BigInteger.valueOf(Long.valueOf(obavestenjeDTO.teloObavestenja.adresa.postanskiBroj)));
        aresaOrgana.setUlica(obavestenjeDTO.teloObavestenja.adresa.ulica);

        teloObavestenja.getContent().add(new JAXBElement<TAdresa>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Adresa"), TAdresa.class, aresaOrgana));

        teloObavestenja.getContent().add(", kancelarija br. ");

        Integer kancelarija = Integer.parseInt(obavestenjeDTO.teloObavestenja.kancelarija);
        teloObavestenja.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Kancelarija"), Integer.class, kancelarija));

        teloObavestenja.getContent().add(" možete izvršiti uvid u dokument/e u kome je sadržana tražena informacija.\n" +
                "        Tom prilikom, na vaš zahtev, može vam se izdati i kopija dokumenta sa traženom informacijom.\n" +
                "        Troškovi su utvrđeni Uredbom Vlade Republike Srbije („Sl. glasnik RS“, br. 8/06), i to: kopija strane A4 formata iznosi 3 dinara, A3 formata 6 dinara, CD 35 dinara, diskete 20 dinara, DVD 40 dinara, audio-kaseta – 150 dinara, video-kaseta 300 dinara, pretvaranje jedne strane dokumenta iz fizičkog u elektronski oblik – 30 dinara.\n" +
                "        Iznos ukupnih troškova izrade kopije dokumenta po vašem zahtevu iznosi ");

        Integer suma = Integer.parseInt(obavestenjeDTO.teloObavestenja.suma);
        teloObavestenja.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Suma"), Integer.class, suma));

        obavestenje.setTeloObavestenja(teloObavestenja);

        TDostavljeno dostavljeno = new TDostavljeno();
        for(OpcijaObavestenjeDTO oo : obavestenjeDTO.opcija){
            if(oo.tekst.equals("архиви"))
                dostavljeno.setArhivi(oo.cekiran);
            else
                dostavljeno.setImenovanom(oo.cekiran);
        }
        obavestenje.setDostavljeno(dostavljeno);

        String id = UUID.randomUUID().toString();
        obavestenje.setAbout("http://localhost:8081/notification/"+id);

        obavestenjeRepository.save(id, obavestenje);
    }

    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

}
