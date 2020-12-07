package rs.ac.uns.ftn.test;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;

import rs.ac.uns.ftn.jaxb.util.MyValidationEventHandler;
import rs.ac.uns.ftn.xml_obavestenja.Obavestenje;
import rs.ac.uns.ftn.xml_obavestenja.ObjectFactory;
import rs.ac.uns.ftn.xml_obavestenja.TDostavljeno;
import rs.ac.uns.ftn.xml_obavestenja.TPodnosilac;
import rs.ac.uns.ftn.xml_obavestenja.TTeloObavestenja;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TOrgan;
import rs.ac.uns.ftn.xml_opste.TZakon;

public class PrimerObavestenje {
	public static void main(String[] argv) throws Exception {
		JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xml_obavestenja");
		Unmarshaller unmarshaller = context.createUnmarshaller();

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("data\\obavestenje.xsd"));

		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());

		Marshaller marshaller = context.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		System.out.println("\n\n\n ======== LOAD AND SHOW REAL DOCUMENT ======= \n\n\n");

		Obavestenje blackmagic = (Obavestenje) unmarshaller.unmarshal(new File("data\\xml\\obavestenje_original.xml"));

		marshaller.marshal(blackmagic, System.out);

		// CREATE NEW
		System.out.println("\n\n\n ======== CREATE NEW VALIDATE ======= \n\n\n");
		ObjectFactory factoryObavestenje = new ObjectFactory();

		Obavestenje novoObavestenje = factoryObavestenje.createObavestenje();

		novoObavestenje.setBrojPredmeta("123-32123");
		TDatum datum = new TDatum();
		datum.setDatum("12.12.2020");
		novoObavestenje.setDatum(datum);
		TDostavljeno dostavljeno = new TDostavljeno();
		dostavljeno.setArhivi(false);
		dostavljeno.setImenovanom(true);
		novoObavestenje.setDostavljeno(dostavljeno);
		novoObavestenje.setNaziv("Naziv obavestenja");
		TOrgan organ = new TOrgan();
		TAdresa adresa = new TAdresa();
		adresa.setBroj(BigInteger.valueOf(1));
		adresa.setDrzava("DrzavaA");
		adresa.setMesto("Mesto");
		adresa.setPostanskiBroj(BigInteger.valueOf(1));
		adresa.setUlica("ulica");
		organ.setAdresa(adresa);
		organ.setNaziv("Naziv organa");
		novoObavestenje.setOrgan(organ);
		TPodnosilac podnosilac = new TPodnosilac();
		podnosilac.setAdresa(adresa);
		podnosilac.setNaziv("naziv firme koja podnosi");
		novoObavestenje.setPodaciPodnosioca(podnosilac);
		TTeloObavestenja telo = new TTeloObavestenja();

		telo.getContent().add("uvodni tekst");
		TZakon zakon = new TZakon();
		zakon.setNazivZakona("MAGNUM KARTA LIBERTATUM");
		telo.getContent().add(new JAXBElement<TZakon>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Zakon"),
				TZakon.class, zakon));
		telo.getContent().add("tekst kojice stojati izmedju");
		telo.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Godina"),
				Integer.class, 2020));
		telo.getContent().add(
				new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Opis"), String.class, ""));
		telo.getContent().add(
				new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Dan"), Integer.class, 31));
		telo.getContent().add(
				new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Sati"), Integer.class, 9));
		telo.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Pocetni_sat"),
				Integer.class, 8));
		telo.getContent().add("traje do");
		telo.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Zavrsni_sat"),
				Integer.class, 12));
		telo.getContent().add(new JAXBElement<TAdresa>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Adresa"),
				TAdresa.class, adresa));
		telo.getContent().add(new JAXBElement<Integer>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Kancelarija"),
				Integer.class, 12));
		telo.getContent().add(new JAXBElement<Double>(new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Suma"),
				Double.class, 321.21));

		novoObavestenje.setTeloObavestenja(telo);

		marshaller.marshal(novoObavestenje, new FileOutputStream("data\\xml\\obavestenje_1.xml"));

		blackmagic = (Obavestenje) unmarshaller.unmarshal(new File("data\\xml\\obavestenje_1.xml"));

		marshaller.marshal(blackmagic, System.out);
	}
}
