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
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import rs.ac.uns.ftn.jaxb.util.MyValidationEventHandler;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TOrgan;
import rs.ac.uns.ftn.xml_opste.TOsoba;
import rs.ac.uns.ftn.xml_resenje.Resenje;
import rs.ac.uns.ftn.xml_resenje.TSekcija;
import rs.ac.uns.ftn.xml_resenje.Zalba;
import rs.ac.uns.ftn.xml_zahtev.Zahtev;

public class PrimerResenje {

	public static void main(String[] args) throws JAXBException, FileNotFoundException, SAXException {
		// TODO Auto-generated method stub
		JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xml_resenje");
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Resenje resenje = (Resenje) unmarshaller.unmarshal(new File("data\\xml\\resenje_original.xml"));
		
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(resenje, System.out);
		
		rs.ac.uns.ftn.xml_resenje.ObjectFactory factoryResenje = new rs.ac.uns.ftn.xml_resenje.ObjectFactory();

		Resenje novoResenje = factoryResenje.createResenje();
		
		TDatum datum = new TDatum();
		datum.setDatum("datum resenja");
		
		novoResenje.setDatum(datum);
		
		Zalba zalba = new Zalba();
		zalba.setBroj("broj zalbe");
		zalba.setDatum(datum);
		
		novoResenje.setZalba(zalba);
		
		TSekcija sekcija = new TSekcija();
		
		sekcija.getContent().add("Sadrzaj sekcije");	
		
		TSekcija sekcijaResenje = new TSekcija();
		
		sekcijaResenje.setNaziv("RESENJE");
		
		TSekcija sekResenje1 = new TSekcija();
		sekResenje1.getContent().add("Sadrzaj sekcije resenje 1");
		TSekcija sekResenje2 = new TSekcija();
		sekResenje2.getContent().add("Sadrzaj sekcije resenje 2");
		
		JAXBElement<TSekcija> tsekcije1 = new JAXBElement(new QName("http://ftn.uns.ac.rs/xml_resenje", "sekcija"), TSekcija.class, sekResenje1);
		JAXBElement<TSekcija> tsekcije2 = new JAXBElement(new QName("http://ftn.uns.ac.rs/xml_resenje", "sekcija"), TSekcija.class, sekResenje2);

		sekcijaResenje.getContent().add(tsekcije1);
		sekcijaResenje.getContent().add(tsekcije2);
		
		TSekcija sekcijaObrazlozenje = new TSekcija();
		
		sekcijaObrazlozenje.setNaziv("OBRAZLOZENJE");
		sekcijaObrazlozenje.getContent().add("Sadrzaj sekcije obrazlozenje");
		
		novoResenje.getSekcija().add(sekcija);
		novoResenje.getSekcija().add(sekcijaResenje);
		novoResenje.getSekcija().add(sekcijaObrazlozenje);
		
		for(TSekcija s: novoResenje.getSekcija())
			System.out.println(s.getNaziv());
		
		TOsoba poverenik = new TOsoba();
		poverenik.setIme("Pera");
		poverenik.setPrezime("Peric");
		
		novoResenje.setPoverenik(poverenik);
		
		System.out.println(novoResenje);
		
		

		
		marshaller.marshal(novoResenje, new FileOutputStream("data\\xml\\resenje_end.xml"));
	
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("data\\resenje.xsd"));
        
		// Podešavanje unmarshaller-a za XML schema validaciju
		unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new MyValidationEventHandler());
		
		Resenje blackmagic = (Resenje) unmarshaller.unmarshal(new File("data\\xml\\resenje_end.xml"));
		
		System.out.println(blackmagic);
		marshaller.marshal(blackmagic, System.out);

		
	}

}
