package rs.ac.uns.ftn.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.GregorianCalendar;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
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
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("C:\\Users\\Zdravko\\Documents\\Lab\\fax\\XMLProjekat\\data\\xml\\zahtev1.xml"));
		
		/*
		JAXBElement<?> ser = (JAXBElement<?>)zahtev.getTeloZahteva().getParagraf().getContent().get(1);
		TTipoviZahteva ttip = (TTipoviZahteva)ser.getValue();
		
		System.out.println(ttip.getTipZahteva().get(1).getContent().get(0));
		*/
		/*
		JAXBElement<TTipoviZahteva> ser = (JAXBElement<TTipoviZahteva>)zahtev.getTeloZahteva().getParagraf().getContent().get(1);
		System.out.println(ser.getValue().getClass());
		
		System.out.println(zahtev);*/
		
		// Instanciranje Studenti klase posredstvom ObjectFactory-a
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
		
		JAXBElement<TTipoviDostave> jaxbTTipovi = new JAXBElement(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_dostave"), TTipoviDostave.class, tipoviDostave);
		
		tip3.getContent().add(jaxbTTipovi);
		
		tipovi.getTipZahteva().add(tip1);
		tipovi.getTipZahteva().add(tip2);
		tipovi.getTipZahteva().add(tip3);
		
		paragraf.getContent().add("Tekst paragrafa\n");
		JAXBElement<TTipoviZahteva> jaxbTipovi = new JAXBElement(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_zahteva"), TTipoviZahteva.class, tipovi);
		paragraf.getContent().add(jaxbTipovi);
		
		telo.setParagraf(paragraf);
		
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
		
		
		// Generiše se novi student
		// studenti.getStudent().add(createStudent(12345, "Tijana", "Novkovic"));
				
		
	
		Marshaller marshaller = context.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.marshal(noviZahtev, new FileOutputStream("C:\\Users\\Zdravko\\Documents\\Lab\\fax\\XMLProjekat\\data\\xml\\magic.xml"));
	
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("C:\\Users\\Zdravko\\Documents\\Lab\\fax\\XMLProjekat\\data\\zahtev.xsd"));
        
		// Podešavanje unmarshaller-a za XML schema validaciju
		unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new MyValidationEventHandler());
		
		Zahtev blackmagic = (Zahtev) unmarshaller.unmarshal(new File("C:\\Users\\Zdravko\\Documents\\Lab\\fax\\XMLProjekat\\data\\xml\\magic.xml"));
		
		System.out.println(blackmagic);
		marshaller.marshal(blackmagic, System.out);
	}

}
