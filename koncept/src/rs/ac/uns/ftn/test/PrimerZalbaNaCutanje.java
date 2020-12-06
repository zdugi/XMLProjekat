package rs.ac.uns.ftn.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import rs.ac.uns.ftn.jaxb.util.MyValidationEventHandler;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TDodatneInformacije;
import rs.ac.uns.ftn.xml_opste.TOrgan;
import rs.ac.uns.ftn.xml_opste.TOsoba;
import rs.ac.uns.ftn.xml_opste.TTrazilac;
import rs.ac.uns.ftn.xml_zalba_na_cutanje.ObjectFactory;
import rs.ac.uns.ftn.xml_zalba_na_cutanje.TRazloziZalbe;
import rs.ac.uns.ftn.xml_zalba_na_cutanje.TRazloziZalbe.RazlogZalbe;
import rs.ac.uns.ftn.xml_zalba_na_cutanje.TTeloZalbe;
import rs.ac.uns.ftn.xml_zalba_na_cutanje.ZalbaNaCutanje;

public class PrimerZalbaNaCutanje {
	public static void main(String[] argv) throws Exception {
				System.out.println("\n\n\n ======== LOAD AND SHOW REAL DOCUMENT ======= \n\n\n");
				JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xml_zalba_na_cutanje");
				
				Unmarshaller unmarshaller = context.createUnmarshaller();
				ZalbaNaCutanje zahtev = (ZalbaNaCutanje) unmarshaller.unmarshal(new File("data\\xml\\zalbanacutanje1.xml"));
				
				Marshaller marshaller = context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(zahtev, System.out);
				
				
				// CREATE NEW
				System.out.println("\n\n\n ======== CREATE NEW VALIDATE ======= \n\n\n");
				
				ZalbaNaCutanje novaZalba = napraviZalbu();
				marshaller.marshal(novaZalba, new FileOutputStream("data\\xml\\zalbanacutanje_nova.xml"));
				
				SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = schemaFactory.newSchema(new File("data\\zalbanacutanje.xsd"));
			
				unmarshaller.setSchema(schema);
		        unmarshaller.setEventHandler(new MyValidationEventHandler());
		        
		        ZalbaNaCutanje blackmagic = (ZalbaNaCutanje) unmarshaller.unmarshal(new File("data\\xml\\zalbanacutanje_nova.xml"));
				
				marshaller.marshal(blackmagic, System.out);
				
	}
	
private static ZalbaNaCutanje napraviZalbu() {
		
		ObjectFactory factory = new ObjectFactory();
		
		ZalbaNaCutanje zalba = factory.createZalbaNaCutanje();
		
		zalba.setNaziv("ZALBA KADA ORGAN NIJE POSTUPIO/nije postupio u celosti/ PO ZAHTEVU TRAZIOCA U ZAKONSKOM ROKU (CUTANJE UPRAVE)");
        
        TAdresa adresaPrimaoca = new TAdresa();
        adresaPrimaoca.setDrzava("Srbija");
        adresaPrimaoca.setMesto("Novi Sad");
        adresaPrimaoca.setBroj(BigInteger.valueOf(15l));
        adresaPrimaoca.setPostanskiBroj(BigInteger.valueOf(11000l));
        adresaPrimaoca.setUlica("Bulevar kralja Aleksandra");
        
        zalba.setAdresaPrimaoca(adresaPrimaoca);
        
        zalba.setPrimalac("Poverenik za informacije od javnog znacaja i "
        		+ "zastitu podataka o licnosti");
    	
        TTeloZalbe teloZalbe = new TTeloZalbe();
        List<Serializable> list = teloZalbe.getContent();
        
        list.add("U skladu sa clanom 22. Zakona o slobodnom pristupu informacijama od javnog znacaja podnosim: \n");
        list.add("ZALBU protiv");
        
        TOrgan organDonosilacOdluke = new TOrgan();
        organDonosilacOdluke.setNaziv("Agencija za drzavnu upravu");
        JAXBElement<TOrgan> organDonosilacOdlukeJax = new JAXBElement<TOrgan>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Organ"), TOrgan.class, organDonosilacOdluke);
		list.add(organDonosilacOdlukeJax);
        
		TRazloziZalbe razloziZalbe = new TRazloziZalbe();
		
		RazlogZalbe razlogZalbe1 = new RazlogZalbe();
		razlogZalbe1.setValue(" nije postupio");
		razlogZalbe1.setOdabrano(true);
		RazlogZalbe razlogZalbe2 = new RazlogZalbe();
		razlogZalbe2.setValue(" nije postupio u celosti");
		razlogZalbe2.setOdabrano(false);
		RazlogZalbe razlogZalbe3 = new RazlogZalbe();
		razlogZalbe3.setValue(" u zakonskom roku");
		razlogZalbe3.setOdabrano(false);
		razloziZalbe.getRazlogZalbe().add(razlogZalbe1);
		razloziZalbe.getRazlogZalbe().add(razlogZalbe2);
		razloziZalbe.getRazlogZalbe().add(razlogZalbe3);
		
		JAXBElement<TRazloziZalbe> razloziZalbeJax = new JAXBElement<TRazloziZalbe>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Razlozi_zalbe"), TRazloziZalbe.class, razloziZalbe);
		list.add(razloziZalbeJax);
		
		list.add("po mom zahtevu  za slobodan pristup informacijama od javnog znacaja koji sam podneo  tom organu  dana ");
		
		TDatum datumPodnosenjaOdluke = new TDatum();
		datumPodnosenjaOdluke.setDatum("13/3/2013");
		JAXBElement<TDatum> datumPodnosenjaOdlukeJax = new JAXBElement<TDatum>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Datum_podnosenja_zahteva"), TDatum.class, datumPodnosenjaOdluke);
		list.add(datumPodnosenjaOdlukeJax);
		
		list.add("\ngodine, a kojim sam trazio/la da mi se u skladu sa Zakonom o slobodnom pristupu informacijama od javnog znacaja omoguci uvid- kopija dokumenta koji sadrzi informacije  o /u vezi sa :\n\t");
		
		String opisZalbe = " informacije koje su trazene";
		JAXBElement<String> opisZalbeJax  = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Podaci_o_zahtevu_i_informacija"), String.class, opisZalbe);
		list.add(opisZalbeJax);
		
		list.add("\n(navesti podatke o zahtevu i informaciji/ama)");
		
		list.add("Na osnovu iznetog, predlazem da Poverenik uvazi moju zalbu i omoguci mi pristup trazenoj/im  informaciji/ma. \n Kao dokaz , uz zalbu dostavljam kopiju zahteva sa dokazom o predaji organu vlasti.\n\t");
        
        zalba.setTeloZalbe(teloZalbe);
        
        TOsoba osoba = new TOsoba();
        osoba.setIme("Mira");
        osoba.setPrezime("Miric");
        
        TAdresa adresaOsobe = new TAdresa();
        adresaOsobe.setUlica("Bulevar oslobodjenja");
        adresaOsobe.setDrzava("Srbija");
        adresaOsobe.setBroj(BigInteger.valueOf(74l));
        adresaOsobe.setMesto("Novi Sad");
        adresaOsobe.setPostanskiBroj(BigInteger.valueOf(21000L));
        
        TTrazilac trazilac = new TTrazilac();
        trazilac.setAdresa(adresaOsobe);
        trazilac.setKontakt("063/0000-111");
        trazilac.setOsoba(osoba);
        
        TDatum datum = new TDatum();
        datum.setDatum("6/12/2020");
        
        TDodatneInformacije dodatneInformacije = new TDodatneInformacije();
        dodatneInformacije.setMesto("Novi Sad");
        dodatneInformacije.setTrazilac(trazilac);
        dodatneInformacije.setDatum(datum);
        
        zalba.setDodatneInformacije(dodatneInformacije);
        
        String napomena = " Kod zalbe  zbog nepostupanju po zahtevu u celosti, treba priloziti i dobijeni odgovor organa vlasti.";
		JAXBElement<String> napomenaJax  = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Napomena"), String.class, napomena);
		list.add(napomenaJax);
        return zalba;
	}
}
