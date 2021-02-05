package com.xmlproject.project_poverenik.service;

import com.itextpdf.text.DocumentException;
import com.xmlproject.project_poverenik.model.poruka.Poruka;
import com.xmlproject.project_poverenik.model.xml_korisnik.Korisnik;
import com.xmlproject.project_poverenik.model.xml_opste.*;
import com.xmlproject.project_poverenik.model.xml_opste.TTrazilac;
import com.xmlproject.project_poverenik.model.xml_zahtev.*;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ObjectFactory;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.TRazloziZalbe;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.TTeloZalbe;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.xmlproject.project_poverenik.repository.ZalbaNaCutanjeRepository;
import com.xmlproject.project_poverenik.ws.poruka.PorukaInterface;
import com.xmlproject.project_poverenik.ws.zahtev.ZahtevInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.xmlproject.project_poverenik.service.*;
import org.xmldb.api.base.XMLDBException;
import pojo.ComplaintsAdvanceSearchQuery;
import pojo.ComplaintsListDTO;
import pojo.DostavaDTO;
import pojo.ZalbaNaCutanjeDTO;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ZalbaNaCutanjeService extends AbsService {

    @Autowired
    private ZalbaNaCutanjeRepository zalbaNaCutanjeRepository;

    @Autowired
    private PorukaService porukaService;

    public ZalbaNaCutanjeService() {
        //Repository repository, String xslPath, String fontPath
        super("src/main/resources/zalba_na_cutanje_temp.xsl","src/main/resources/FreeSans.ttf");
    }

    public void create (ZalbaNaCutanjeDTO zalbaNaCutanje) throws Exception {
        URL wsdlLocation = new URL("http://localhost:8089/ws/request?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/request", "ZahtevService");
        QName portName = new QName("http://soap.spring.com/ws/request", "ZahtevPort");

        javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);

        ZahtevInterface zahtevInterface = service.getPort(portName, ZahtevInterface.class);

        Zahtev zahtev = zahtevInterface.getRequest(zalbaNaCutanje.idZahteva);

        if (zahtev == null){
            throw new Exception("Zahtev sa unetim ID-jem ne postoji ili se desila neka greska!");
        }

        Korisnik userDetails = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ObjectFactory factory = new ObjectFactory();

        ZalbaNaCutanje newZalba = factory.createZalbaNaCutanje();

        newZalba.setPrimalac("Повереник за иинформације од јавног значаја и заштиту података о личности");

        ZalbaNaCutanje.Status status = new ZalbaNaCutanje.Status();
        status.setProperty("pred:status");
        status.setValue("нова");
        newZalba.setStatus(status);

        System.out.println(zalbaNaCutanje.idZahteva + " je poslati id zahteva.");

        // adresa primaoca, hardkodovana
        TAdresa adresa = new TAdresa();
        adresa.setBroj(BigInteger.valueOf(15L));

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

        // ovo treba da dobavim iz zahteva
        TOrgan organ = new TOrgan();
        TOrgan.Naziv naziv = new TOrgan.Naziv();
        naziv.setProperty("pred:upucujeSe");
        naziv.setValue(zahtev.getOrgan().getNaziv().getValue());
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
        //JAXBElement<String> stringTelo = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje"), String.class, tekst);
        tTeloZalbe.getContent().add(tekst);


        // ovaj datum izvlacim iz zahteva
        TDatum datum = new TDatum();
        datum.setValue(zahtev.getDodatneInformacije().getDatum().getValue());
        JAXBElement<TDatum> datumTelo = new JAXBElement<TDatum>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Datum_podnosenja_zahteva"), TDatum.class, datum);
        tTeloZalbe.getContent().add(datumTelo);

        String tekst1 = "године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са :";
        //JAXBElement<String> stringTelo1 = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje"), String.class, tekst1);
        tTeloZalbe.getContent().add(tekst1);

        JAXBElement<String> stringTelo2 = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Podaci_o_zahtevu_i_informacija"), String.class, zalbaNaCutanje.podaciOZahtevuIInformacijama.podaci);
        tTeloZalbe.getContent().add(stringTelo2);

        String tekst2 = "(навести податке о захтеву и информацији/ама)\n" +
                "\n" +
                "На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им информацији/ма.";
        //JAXBElement<String> stringTelo3 = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje"), String.class, tekst2);
        tTeloZalbe.getContent().add(tekst2);

        String napomena = "Напомена: Код жалбе због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.";
        JAXBElement<String> napomenaTelo = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Napomena"), String.class, napomena);
        tTeloZalbe.getContent().add(napomenaTelo);

        newZalba.setTeloZalbe(tTeloZalbe);

        TDodatneInformacije tDodatneInformacije = new TDodatneInformacije();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        TDatum datumDI = new TDatum();
        datumDI.setValue(formatter.format(date));
        datumDI.setProperty("pred:podnosenje");

        tDodatneInformacije.setDatum(datumDI);

        TOsoba osoba = new TOsoba();
        osoba.setIme(userDetails.getLicneInformacije().getOsoba().getIme());
        osoba.setPrezime(userDetails.getLicneInformacije().getOsoba().getPrezime());

        TAdresa tAdresa = new TAdresa();
        //todo: pass in form
        TAdresa.Mesto mestoDI = new TAdresa.Mesto();
        mestoDI.setValue(userDetails.getLicneInformacije().getAdresa().getMesto().getValue());
        tAdresa.setMesto(mestoDI);

        com.xmlproject.project_poverenik.model.xml_opste.TTrazilac trazilac = new TTrazilac();
        trazilac.setAdresa(tAdresa);
        trazilac.setKontakt("[uzimam iz sesije]");      // staviti kontakt podnosioca/ulogovanog, ja ga nemam u bazi
        trazilac.setOsoba(osoba);                       // pa bi puklo da uradim .getContact()

        tDodatneInformacije.setTrazilac(trazilac);

        newZalba.setDodatneInformacije(tDodatneInformacije);

        // set naziv
        newZalba.setNaziv("ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)");

        // id setup
        String id = UUID.randomUUID().toString();
        newZalba.setAbout("http://localhost:8081/complaint/" + id);
        trazilac.setRel("pred:potrazuje");
        trazilac.setHref("http://localhost:8081/korisnik/" + userDetails.getId());


        zalbaNaCutanjeRepository.save(id, newZalba);
    }

    public ZalbaNaCutanje getOne (String id) throws Exception {
        return zalbaNaCutanjeRepository.getOne(id);
    }

    public StringWriter generateHTML(String id) throws FileNotFoundException {
        return this.generateHTML(id, zalbaNaCutanjeRepository);
    }

    public String[] getList() throws XMLDBException, IllegalAccessException, InstantiationException {
        return zalbaNaCutanjeRepository.listComplaints();
    }

    public ByteArrayOutputStream generatePDF(String id) throws IOException, DocumentException {
        return this.generatePDF(id, zalbaNaCutanjeRepository);
    }

    @Override
    public ByteArrayOutputStream getOneRDF(String id) throws Exception {
        return zalbaNaCutanjeRepository.getOneMetadataRDF(id);
    }

    @Override
    public ByteArrayOutputStream getOneJSON(String id) throws Exception {
        return zalbaNaCutanjeRepository.getOneMetadataJSON(id);
    }

    public ComplaintsListDTO searchText(String query) {
        try {
            return zalbaNaCutanjeRepository.searchText(query);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (XMLDBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ByteArrayOutputStream queryRDF(ComplaintsAdvanceSearchQuery query) {
        String sparqlQuery = "SELECT * FROM <http://localhost:8080/fuseki/EDataset/data/example/zalbanacutanje/metadata>\n" +
                "WHERE {\n" +
                "  ?subject <http://localhost/predikati/podnosenje> ?datumPodnosenja .\n" +
                "  ?subject <http://localhost/predikati/upucujeSe> ?organKomeSeUpucuje .\n" +
                "  ?subject <http://localhost/predikati/mestoOrgana> ?mestoOrgana .\n" +
                "  ?subject <http://localhost/predikati/drzavaOrgana> ?drzavaOrgana .\n" +
                "  ?subject <http://localhost/predikati/potrazuje> ?trazilac .\n" +
                "  FILTER (regex(str(?datumPodnosenja), \"%s\")) .\n" +
                "  FILTER (regex(str(?organKomeSeUpucuje), \"%s\")) .\n" +
                "  FILTER (regex(str(?mestoOrgana), \"%s\")) .\n" +
                "  FILTER (regex(str(?drzavaOrgana), \"%s\")) .\n" +
                "  FILTER (regex(str(?trazilac), \"%s\")) .\n" +
                "}\n" +
                "LIMIT 100";

        // NO ESCAPE!
        sparqlQuery = String.format(
                sparqlQuery,
                query.submissionDateRegex,
                query.authorityRegex,
                query.placeRegex,
                query.stateRegex,
                query.applicantRegex);

        return zalbaNaCutanjeRepository.queryRDF(sparqlQuery);
    }

    public void setPrihvaceno(String id, String status){
        try {
            zalbaNaCutanjeRepository.setPrihvaceno(id, status);
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<?> getAllXMLInCollection() throws Exception {
        return zalbaNaCutanjeRepository.getAllXMLInCollection();
    }

    public ArrayList<?> getAllXMLInCollectionUpdateStatus() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        ArrayList<ZalbaNaCutanje> zalbe = zalbaNaCutanjeRepository.getAllXMLInCollection();
        String danasString = formatter.format(new Date());
        Date danasnjiDatum = formatter.parse(danasString);

        for (ZalbaNaCutanje zalba: zalbe){
            if (zalba.getStatus().getValue().equals("нова")){
                Date datumZalbe = formatter.parse(zalba.getDodatneInformacije().getDatum().getValue());
                long diffInMillies = Math.abs(danasnjiDatum.getTime() - datumZalbe.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if (diff > 2){
                    // isteklo je vreme da sluzbenik obavesti organ, sistem obavestava organ
                    // poslati poruku
                    String status = "чека се одговор органа власти";
                    ZalbaNaCutanje.Status statusObj = new ZalbaNaCutanje.Status();
                    statusObj.setValue(status);
                    zalba.setStatus(statusObj);
                    sendMessageBruteForce(zalba.getId(), status);
                    this.zalbaNaCutanjeRepository.setPrihvaceno(zalba.getId(), status);
                }
            }
            else if (zalba.getStatus().getValue().equals("чека се одговор органа власти")){
                // ako je proteklo vrijeme, postavlja se na
                // чека решење
                Poruka poruka;
                try {
                    poruka = porukaService.findByComplaint("Poverenik: Podneta je zalba http://localhost:8081/api/complaint/pdf/" + zalba.getId().substring(0, zalba.getId().length() - 4));
                    poruka.getVreme();
                } catch(Exception e){
                    continue;
                }
                    Date datum = new Date(poruka.getVreme().longValue());
                    String dateStr = formatter.format(datum);
                    Date datumPoruke = formatter.parse(dateStr);
                    long diffInMillies = Math.abs(danasnjiDatum.getTime() - datumPoruke.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    if (diff > 5) {
                        String status = "чека решење";
                        ZalbaNaCutanje.Status statusObj = new ZalbaNaCutanje.Status();
                        statusObj.setValue(status);
                        zalba.setStatus(statusObj);
                        sendMessageBruteForce(zalba.getId(), status);
                        this.zalbaNaCutanjeRepository.setPrihvaceno(zalba.getId(), status);
                    }
            }
        }
        return zalbe;
    }

    private boolean sendMessageBruteForce(String id, String status) throws Exception {
        URL wsdlLocation = new URL("http://localhost:8089/ws/message?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/message", "PorukaService");
        QName portName = new QName("http://soap.spring.com/ws/message", "PorukaPort");
        javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);
        PorukaInterface porukaI = service.getPort(portName, PorukaInterface.class);
        Poruka msg = new Poruka();
        msg.setTelo("");
        try {
            String link = "";
            this.setPrihvaceno(id.substring(0, id.length() - 4), status);
            link = "http://localhost:8081/api/complaint/pdf/" + id;

            String mess = "Podneta je zalba " + link + ". Molimo vas da se u roku od 5 dana izjasnite da li" +
                    " zalbu odbijate ili prihvatate.";
            msg.setTelo("Poverenik: " + mess);
        }
        catch (Exception e){
        }
        msg.setVreme(BigInteger.valueOf(System.currentTimeMillis() / 1000L));
        if(!porukaI.sendMessage(msg))
            return false;
        porukaService.saveMessage(msg);
        return true;
    }
}
