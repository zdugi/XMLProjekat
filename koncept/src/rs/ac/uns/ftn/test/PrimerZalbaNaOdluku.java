package rs.ac.uns.ftn.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

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
import rs.ac.uns.ftn.xml_opste.TDodatneInformacije;
import rs.ac.uns.ftn.xml_opste.TIdResenja;
import rs.ac.uns.ftn.xml_opste.TOrgan;
import rs.ac.uns.ftn.xml_opste.TOsoba;
import rs.ac.uns.ftn.xml_opste.TTrazilac;
import rs.ac.uns.ftn.xml_opste.TZakon;
import rs.ac.uns.ftn.xml_zalbanaresenje.ObjectFactory;
import rs.ac.uns.ftn.xml_zalbanaresenje.TTeloZalbeResenje;
import rs.ac.uns.ftn.xml_zalbanaresenje.ZalbaNaResenje;
import rs.ac.uns.ftn.xml_zalbanaresenje.ZalbaNaResenje.Napomene;

public class PrimerZalbaNaOdluku {

	public static void main(String[] args) throws JAXBException, SAXException, FileNotFoundException {
		
		JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xml_zalbanaresenje");
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("data\\zalbanaresenje.xsd"));
        
		
		// Podešavanje unmarshaller-a za XML schema validaciju
		unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new MyValidationEventHandler());
		
        // citanje originala
        ZalbaNaResenje zalba1 = (ZalbaNaResenje) unmarshaller.unmarshal(new File("data\\xml\\zalbanaresenje_original.xml"));
        
        // citanje izmenjene zalbe
        ZalbaNaResenje izmenjena = (ZalbaNaResenje) unmarshaller.unmarshal(new File("data\\xml\\zalbanaresenje_izmenjena.xml"));

        // citanje nove zalbe, samo ako je prethodno generisana
        ZalbaNaResenje nova = (ZalbaNaResenje) unmarshaller.unmarshal(new File("data\\xml\\zalbanaresenje_nova.xml"));
	

        
       
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		System.out.println("\n\n===== SHOW ORIGINAL =====\n\n");
		marshaller.marshal(zalba1, System.out);
		
		System.out.println("\n\n===== EDIT =====\n\n");
		
		marshaller.marshal(izmenjena, System.out);
		
		System.out.println("\n\n===== NEW =====\n\n");
		
		marshaller.marshal(nova, System.out);
		
		
		// neka izmena
        izmenjena.setPrimalac("Novi primalac");
		
		
		//printZalba(zalba2);
		//marshaller.marshal(zalba, System.out);

		marshaller.marshal(izmenjena, new FileOutputStream("data\\xml\\zalbanaresenje_izmenjena.xml"));
	
		
		// programski kreiranje nove zalbe i njen upis
		ZalbaNaResenje novaZalba = napraviZalbu();
		marshaller.marshal(novaZalba, new FileOutputStream("data\\xml\\zalbanaresenje_nova.xml"));
	
		
		//marshaller.marshal(napraviZalbu(), new FileOutputStream("D:\\xml_projekat\\XMLProjekat\\koncept\\data\\temp_zalbanaresenje_izprojekta1_updated2.xml"));
		

	}

	// ekstrahovanje elemenata i print
	private static void printZalba(ZalbaNaResenje zalba) {
		
		System.out.println("Naziv dokumenta: " + zalba.getNaziv());
		System.out.println("Primalac: " + zalba.getPrimalac());
		TAdresa adresa = zalba.getAdresaPrimaoca();
		System.out.println("Adresa primaoca: " + adresa.getMesto() + 
				", " + adresa.getUlica()  + " " + adresa.getBroj());
		TTeloZalbeResenje telo = zalba.getTeloZalbeNaOdluku();
		for (Serializable s: telo.getContent()) {
			try {
				JAXBElement<?> ser = (JAXBElement<?>)s;
				String tagFullName = ser.getName().toString();
				String tagName = tagFullName.substring(tagFullName.indexOf("}") + 1);
				if (tagName.equals("ZalilacOsoba")) {
					TOsoba osoba = (TOsoba)ser.getValue();
					System.out.println("Zalilac: " + osoba.getIme() + " " + osoba.getPrezime());
				}
				else if (tagName.equals("ZalilacOrgan")) {
					
				}
				else if (tagName.equals("Adresa")) {
					TAdresa adresaZalioca = (TAdresa)ser.getValue();
					System.out.println("Adresa zalioca: " + adresaZalioca.getMesto());
				}
				else if (tagName.equals("OrganDonosilacOdluke")) {
					TOrgan organDonosilacOdluke = (TOrgan)ser.getValue();
					System.out.println("Organ donosilac odluke: " + organDonosilacOdluke.getNaziv());
				}
				else if (tagName.equals("BrojResenja")) {
					TIdResenja idResenja = (TIdResenja)ser.getValue();
					System.out.println("Broj resenja: " + idResenja.getBroj() + " " + idResenja.getGodina());
				}
				else if (tagName.equals("Datum_podnosenja_zahteva")) {
					TDatum datumPodnosenja = (TDatum)ser.getValue();
					System.out.println("Datum podnosenja zahteva: " + datumPodnosenja.getDatum());
				}
				else if (tagName.equals("OpisZalbe")) {
					System.out.println("Opis zalbe: " + (String)ser.getValue());
				}
				else if (tagName.equals("Zakon")) {
					TZakon zakon = (TZakon)ser.getValue();
					System.out.println("Zakon: " + zakon.getNazivZakona());
				}
				else {
					continue;
				}
		}
		catch(Exception e) {
			try {
				String str = (String)s;
				System.out.println("Tekst: " +  str);
			}
			catch(Exception ex) {
				
			}
			
		}
		}
		TDodatneInformacije dodatno = zalba.getDodatneInformacije();
		System.out.println();
		TTrazilac trazilac = dodatno.getTrazilac();
		System.out.println("Trazilac: " + trazilac.getOsoba().getIme() + " "+ trazilac.getOsoba().getPrezime());
		System.out.println("Kontakt: " + trazilac.getKontakt());
		System.out.println("Adresa: " + trazilac.getAdresa().getUlica() + " " + trazilac.getAdresa().getBroj());
		System.out.println("Mesto: " + dodatno.getMesto());
		System.out.println("Datum: " + dodatno.getDatum().getDatum());
		Napomene napomene  = zalba.getNapomene();
		System.out.println("Napomene: ");
		for (String n: napomene.getNapomena()) {
			System.out.println("*" + n);
		}
	}


	private static ZalbaNaResenje napraviZalbu() {
		
		ObjectFactory factory = new ObjectFactory();
		
		ZalbaNaResenje zalba = factory.createZalbaNaResenje();
		
		zalba.setNaziv("ZALBA  PROTIV  ODLUKE ORGANA  VLASTI KOJOM JE " + 
        		"ODBIJEN ILI ODBACEN ZAHTEV ZA PRISTUP INFORMACIJI");
        
        TAdresa adresaPrimaoca = new TAdresa();
        adresaPrimaoca.setDrzava("Srbija");
        adresaPrimaoca.setMesto("Nis");
        adresaPrimaoca.setBroj(BigInteger.valueOf(15l));
        adresaPrimaoca.setPostanskiBroj(BigInteger.valueOf(11000l));
        adresaPrimaoca.setUlica("Bulevar kralja Petra");
        
        zalba.setAdresaPrimaoca(adresaPrimaoca);
        
        zalba.setPrimalac("Poverenik za informacije od javnog znacaja i "
        		+ "zastitu podataka o licnosti");
    	
        TTeloZalbeResenje teloZalbe = new TTeloZalbeResenje();
        List<Serializable> list = teloZalbe.getContent();
        
        list.add("ZALBA");
        
        TOsoba osoba = new TOsoba();
        osoba.setIme("Novko");
        osoba.setPrezime("Novakovic");
        
        TAdresa adresaOsobe = new TAdresa();
        adresaOsobe.setUlica("Bulevar oslobodjenja");
        adresaOsobe.setDrzava("Srbija");
        adresaOsobe.setBroj(BigInteger.valueOf(74l));
        adresaOsobe.setMesto("Nis");
        adresaOsobe.setPostanskiBroj(BigInteger.valueOf(21000));
        JAXBElement<TAdresa> adresaOsobeJax = new JAXBElement<TAdresa>(new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "Adresa"), TAdresa.class, adresaOsobe);
		
        
        
        JAXBElement<TOsoba> osobaZalilac = new JAXBElement<TOsoba>(new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "ZalilacOsoba"), TOsoba.class, osoba);
		list.add(osobaZalilac);
        list.add(adresaOsobeJax);
        
        list.add("protiv resenja-zakljucka");
        
        TOrgan organDonosilacOdluke = new TOrgan();
        organDonosilacOdluke.setNaziv("Agencija za drzavnu upravu");
        JAXBElement<TOrgan> organDonosilacOdlukeJax = new JAXBElement<TOrgan>(new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "OrganDonosilacOdluke"), TOrgan.class, organDonosilacOdluke);
		list.add(organDonosilacOdlukeJax);
        
		
		TIdResenja idResenja = new TIdResenja();
		idResenja.setBroj("192-23");
		idResenja.setGodina(BigInteger.valueOf(2019l));
		JAXBElement<TIdResenja> idResenjaJax = new JAXBElement<TIdResenja>(new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "BrojResenja"), TIdResenja.class, idResenja);
		list.add(idResenjaJax);
		
		list.add("Navedenom odlukom organa vlasti (resenjem, zakljuckom, obavestenjem u pisanoj "
				+ "formi sa elementima odluke) , suprotno zakonu, odbijen-odbacen je moj zahtev "
				+ "koji sam podneo/la-uputio/la dana ");
		
		TDatum datumPodnosenjaOdluke = new TDatum();
		datumPodnosenjaOdluke.setDatum("11/1/2010");
		JAXBElement<TDatum> datumPodnosenjaOdlukeJax = new JAXBElement<TDatum>(new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "Datum_podnosenja_zahteva"), TDatum.class, datumPodnosenjaOdluke);
		list.add(datumPodnosenjaOdlukeJax);
		
		list.add("godine i tako mi uskraceno-onemoguceno ostvarivanje ustavnog i zakonskog prava na slobodan pristup informacijama"
				+ " od javnog znacaja. Odluku pobijam u celosti, odnosno u delu kojim");
		
		String opisZalbe = " mi je nepravosnazno onemogucen pristup informacijama...";
		JAXBElement<String> opisZalbeJax  = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "OpisZalbe"), String.class, opisZalbe);
		list.add(opisZalbeJax);
		
		list.add("jer nije zasnovana na Zakonu o slobodnom pristupu informacijama od javnog znacaja.\r\n" + 
				"Na osnovu iznetih razloga, predlazem da Poverenik uvazi moju zalbu,  ponisti odluka prvostepenog organa i omoguci mi pristup trazenoj/im  informaciji/ma.\r\n" + 
				"");
		
		list.add("Zalbu podnosim blagovremeno, u zakonskom roku utvrdenom u ");
		
		TZakon zakon = new TZakon();
		zakon.setNazivZakona("Zakon o slobodnom pristupu informacijama od javnog znacaja");
	
		JAXBElement<TZakon> zakonJax  = new JAXBElement<TZakon>(new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "Zakon"), TZakon.class, zakon);
		list.add(zakonJax);
        
        zalba.setTeloZalbeNaOdluku(teloZalbe);
        
        
        TTrazilac trazilac = new TTrazilac();
        trazilac.setAdresa(adresaOsobe);
        trazilac.setKontakt("063/7009-291");
        trazilac.setOsoba(osoba);
        
        TDatum datum = new TDatum();
        datum.setDatum("5/12/2020");
        
        TDodatneInformacije dodatneInformacije = new TDodatneInformacije();
        dodatneInformacije.setMesto("Novi Sad");
        dodatneInformacije.setTrazilac(trazilac);
        dodatneInformacije.setDatum(datum);
        
        zalba.setDodatneInformacije(dodatneInformacije);
        
        Napomene napomene = new Napomene();
        napomene.getNapomena().add("U zalbi se mora navesti odluka koja se pobija (resenje, zakljucak, obavestenje), "
        		+ "naziv organa koji je odluku doneo, kao i broj i datum odluke. Dovoljno je da zalilac navede u zalbi u"
        		+ " kom pogledu je nezadovoljan odlukom, s tim da zalbu ne mora posebno obrazloziti. "
        		+ "Ako zalbu izjavljuje na ovom obrascu, dodatno obrazlozenje moze  posebno priloziti.");
        napomene.getNapomena().add("Uz zalbu obavezno priloziti kopiju podnetog zahteva i dokaz o njegovoj "
        		+ "predaji-upucivanju organu kao i kopiju odluke organa koja se osporava zalbom.");
        zalba.setNapomene(napomene);
        return zalba;
	}
	
}
