package rs.ac.uns.ftn.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import rs.ac.uns.ftn.jaxb.util.MyValidationEventHandler;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TDodatneInformacije;
import rs.ac.uns.ftn.xml_opste.TOrgan;
import rs.ac.uns.ftn.xml_opste.TOsoba;
import rs.ac.uns.ftn.xml_opste.TTrazilac;
import rs.ac.uns.ftn.xml_zahtev.TInformacije;
import rs.ac.uns.ftn.xml_zahtev.TParagraf;
import rs.ac.uns.ftn.xml_zahtev.TTeloZahteva;
import rs.ac.uns.ftn.xml_zahtev.TTipDostave;
import rs.ac.uns.ftn.xml_zahtev.TTipZahteva;
import rs.ac.uns.ftn.xml_zahtev.TTipoviDostave;
import rs.ac.uns.ftn.xml_zahtev.TTipoviZahteva;
import rs.ac.uns.ftn.xml_zahtev.Zahtev;

public class PrimerZahtev {

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, FileNotFoundException, SAXException {
		JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xml_zahtev");
		Marshaller marshaller = context.createMarshaller();
		Unmarshaller unmarshaller = context.createUnmarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("data\\zahtev.xsd"));
        
		// Podešavanje unmarshaller-a za XML schema validaciju
		unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new MyValidationEventHandler());
		
		//todo: read and show real document
		System.out.println("\n\n\n ======== LOAD AND SHOW REAL DOCUMENT ======= \n\n\n");
		
		Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("data\\xml\\zahtev_original.xml"));
		
		marshaller.marshal(zahtev, System.out);
		
		// CREATE NEW
		System.out.println("\n\n\n ======== CREATE NEW & VALIDATE ======= \n\n\n");
		rs.ac.uns.ftn.xml_zahtev.ObjectFactory factoryZahtev = new rs.ac.uns.ftn.xml_zahtev.ObjectFactory();
		
		Zahtev noviZahtev = factoryZahtev.createZahtev();
		
		TOrgan organ = new TOrgan();
		organ.setNaziv("Naziv organa");
		
		TAdresa adresa = new TAdresa();
		adresa.setBroj(BigInteger.valueOf(1000L));
		adresa.setDrzava("Srbija");
		adresa.setMesto("Novi Sad");
		adresa.setPostanskiBroj(BigInteger.valueOf(21000L));
		adresa.setUlica("Sekspirova");
		
		organ.setAdresa(adresa);
		organ.setNaziv("Naziv organa");
		
		// set organ
		noviZahtev.setOrgan(organ);
		
		TTeloZahteva telo = new TTeloZahteva();
		
		TParagraf paragraf = new TParagraf();
		
		TTipoviZahteva tipovi = new TTipoviZahteva();
		TTipZahteva tip1 = new TTipZahteva();
		TTipZahteva tip2 = new TTipZahteva();
		TTipZahteva tip3 = new TTipZahteva();
		
		tip1.getContent().add("prvi zahtev");
		tip1.setOdabrano(true);
		tip2.getContent().add("drugi zahtev");
		tip2.setOdabrano(false);
		
		tip3.getContent().add("treci zahtev");
		tip3.setOdabrano(false);
		
		TTipoviDostave tipoviDostave = new TTipoviDostave();
		
		TTipDostave dostava1 = new TTipDostave();
		dostava1.setOdabrano(false);
		dostava1.getContent().add("Neki sadrzaj");
		
		JAXBElement<String> dodatniTip = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Dodatni_tip"), String.class, "vrednost dodatnog tipa");
		
		dostava1.getContent().add(dodatniTip);
		
		tipoviDostave.getTipDostave().add(dostava1);
		
		JAXBElement<TTipoviDostave> jaxbTTipovi = new JAXBElement<TTipoviDostave>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_dostave"), TTipoviDostave.class, tipoviDostave);
		
		tip3.getContent().add(jaxbTTipovi);
		
		tipovi.getTipZahteva().add(tip1);
		tipovi.getTipZahteva().add(tip2);
		tipovi.getTipZahteva().add(tip3);
		
		paragraf.getContent().add("Tekst paragrafa\n");
		JAXBElement<TTipoviZahteva> jaxbTipovi = new JAXBElement<TTipoviZahteva>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_zahteva"), TTipoviZahteva.class, tipovi);
		paragraf.getContent().add(jaxbTipovi);
		
		telo.setParagraf(paragraf);
		
		TInformacije informacije = new TInformacije();
		informacije.setNaslov("Naslov");
		informacije.setOpis("Smislen opis");
		informacije.setSavet("Hint");
		
		telo.setInformacije(informacije);
		
		// set telo
		noviZahtev.setTeloZahteva(telo);
		
		// set naziv
		noviZahtev.setNaziv("Naziv zahteva");
		
		TDodatneInformacije dodatne = new TDodatneInformacije();
		TDatum datum = new TDatum();
		
		datum.setDatum("Hallow Vrld");
		
		TOsoba osoba = new TOsoba();
		osoba.setIme("Petar");
		osoba.setPrezime("Petrovic");
		
		TTrazilac trazilac = new TTrazilac();
		trazilac.setAdresa(adresa);
		trazilac.setKontakt("123 456");
		trazilac.setOsoba(osoba);
		
		dodatne.setDatum(datum);
		dodatne.setMesto("Jovanjica");
		dodatne.setTrazilac(trazilac);
		
		// set dodatne
		noviZahtev.setDodatneInformacije(dodatne);
		

		marshaller.marshal(noviZahtev, new FileOutputStream("data\\xml\\magic.xml"));
		
		Zahtev blackmagic = (Zahtev) unmarshaller.unmarshal(new File("data\\xml\\magic.xml"));
		
		// System.out.println(blackmagic);
		marshaller.marshal(blackmagic, System.out);
	}

}
