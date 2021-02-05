package com.xmlproject.project_poverenik.service;

import com.itextpdf.text.DocumentException;
import com.xmlproject.project_poverenik.model.xml_korisnik.Korisnik;
import com.xmlproject.project_poverenik.model.xml_opste.*;
import com.xmlproject.project_poverenik.model.xml_zahtev.Zahtev;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ObjectFactory;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.TTeloZalbeOdluka;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.xmlproject.project_poverenik.repository.ZalbaNaOdlukuRepository;
import com.xmlproject.project_poverenik.ws.zahtev.ZahtevInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import pojo.ComplaintsAdvanceSearchQuery;
import pojo.ComplaintsListDTO;
import pojo.ZalbaNaOdlukuDTO;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
public class ZalbaNaOdlukuService extends AbsService{

    @Autowired
    private ZalbaNaOdlukuRepository zalbaNaOdlukuRepository;

    public ZalbaNaOdlukuService() {
        //Repository repository, String xslPath, String fontPath
        super("src/main/resources/zalba_na_odluku_temp.xsl","src/main/resources/FreeSans.ttf");

    }

    public void create (ZalbaNaOdlukuDTO zalbaNaOdlukuDTO) throws Exception {
        URL wsdlLocation = new URL("http://localhost:8089/ws/request?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/request", "ZahtevService");
        QName portName = new QName("http://soap.spring.com/ws/request", "ZahtevPort");

        javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);

        ZahtevInterface zahtevInterface = service.getPort(portName, ZahtevInterface.class);

        Zahtev zahtev = zahtevInterface.getRequest(zalbaNaOdlukuDTO.idZahteva);

        if (zahtev == null){
            throw new Exception("Zahtev sa unetim ID-jem ne postoji ili se desila neka greska!");
        }

        Korisnik userDetails = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ObjectFactory factory = new ObjectFactory();

        ZalbaNaOdluku zalbaNaOdluku = factory.createZalbaNaOdluku();

        zalbaNaOdluku.setPrimalac("Повереник за иинформације од јавног значаја и заштиту података о личности");

        ZalbaNaOdluku.Status status = new ZalbaNaOdluku.Status();
        status.setProperty("pred:status");
        status.setValue("нова");
        zalbaNaOdluku.setStatus(status);

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

        System.out.println(zalbaNaOdlukuDTO.zalilac);
        System.out.println(" a organ je ");
        System.out.println(zalbaNaOdlukuDTO.organZalilac);

        if (zalbaNaOdlukuDTO.organZalilac == null) {

            TOsoba zalilac = new TOsoba();
            zalilac.setIme(userDetails.getLicneInformacije().getOsoba().getIme());
            zalilac.setPrezime(userDetails.getLicneInformacije().getOsoba().getPrezime());

            JAXBElement<TOsoba> zalilacJ = new JAXBElement<TOsoba>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "ZalilacOsoba"), TOsoba.class, zalilac);
            teloZalbeOdluka.getContent().add(zalilacJ);
        }
        else {
            TOrgan organZalilac = new TOrgan();
            TOrgan.Naziv naziv = new TOrgan.Naziv();
            naziv.setValue(zalbaNaOdlukuDTO.organZalilac.naziv);
            organZalilac.setNaziv(naziv);
            TAdresa adresaOrgana = new TAdresa();
            adresaOrgana.setBroj(BigInteger.valueOf(Long.valueOf(zalbaNaOdlukuDTO.organZalilac.adresa.broj)));
            TAdresa.Drzava drzavaOrgana = new TAdresa.Drzava();
            drzava.setValue(zalbaNaOdlukuDTO.organZalilac.adresa.drzava);
            adresaOrgana.setDrzava(drzavaOrgana);
            TAdresa.Mesto mestoOrgana = new TAdresa.Mesto();
            mestoOrgana.setValue(zalbaNaOdlukuDTO.organZalilac.adresa.mesto);
            adresaOrgana.setMesto(mestoOrgana);
            adresaOrgana.setUlica(zalbaNaOdlukuDTO.organZalilac.adresa.ulica);
            adresaOrgana.setPostanskiBroj(BigInteger.valueOf(Long.valueOf(zalbaNaOdlukuDTO.organZalilac.adresa.postanskiBroj)));
            organZalilac.setAdresa(adresaOrgana);
            JAXBElement<TOrgan> organJ = new JAXBElement<TOrgan>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "ZalilacOrgan"), TOrgan.class, organZalilac);
            teloZalbeOdluka.getContent().add(organJ);

        }
        // zalilac osoba ili zalilac organ, neka zasad bude osoba
        TAdresa adresa1 = new TAdresa();
        adresa1.setBroj(BigInteger.valueOf(Long.valueOf(15000)));

        TAdresa.Drzava drzava1 = new TAdresa.Drzava();
        drzava1.setProperty("pred:drzavaOrgana1");
        drzava1.setValue("Србија na vodi");
        adresa1.setDrzava(drzava1);

        TAdresa.Mesto mesto1 = new TAdresa.Mesto();
        mesto1.setProperty("pred:mestoOrgana1");
        mesto1.setValue("Београд na vodi");
        adresa1.setMesto(mesto1);
        adresa1.setPostanskiBroj(BigInteger.valueOf(11000L));
        adresa1.setUlica("Булевар краља Александра");
        adresa1.setBroj(BigInteger.valueOf(15L));

        JAXBElement<TAdresa> adresa1J = new JAXBElement<TAdresa>(new QName("http://ftn.uns.ac.rs/xml_zalbanaodluku", "Adresa"), TAdresa.class, adresa1);
        //teloZalbeOdluka.getContent().add(adresa1J);   // necu jos da stavim adresu

        teloZalbeOdluka.getContent().add("protiv resenja-zakljucka");


        // ovo dobavljam iz zahteva
        TOrgan organDonosilacOdluke = new TOrgan();
        TOrgan.Naziv naziv = new TOrgan.Naziv();
        naziv.setProperty("pred:upucujeSe");
        naziv.setValue(zahtev.getOrgan().getNaziv().getValue());
        //organDonosilacOdluke.setAdresa(adresa1);    // na primjer, treba izvuci
                                                        // ovde je bila greska
        organDonosilacOdluke.setNaziv(naziv);

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
        datumPodnosenjaZahteva.setValue(zahtev.getDodatneInformacije().getDatum().getValue());
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

        datumDI.setValue("[danasnji datum]");
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
        trazilac.setKontakt(zahtev.getDodatneInformacije().getTrazilac().getKontakt());
        trazilac.setOsoba(osoba);

        tDodatneInformacije.setMesto("Место подношења, ovo sam stavila u servisu");
        tDodatneInformacije.setTrazilac(trazilac);

        zalbaNaOdluku.setTeloZalbeNaOdluku(teloZalbeOdluka);

        zalbaNaOdluku.setDodatneInformacije(tDodatneInformacije);

        zalbaNaOdluku.setNaziv(" ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ \n" +
                "ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ\n");

        ZalbaNaOdluku.Napomene napomene = new ZalbaNaOdluku.Napomene();
        napomene.getNapomena().add("У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити. ");
        napomene.getNapomena().add("•\tУз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу као и копију одлуке органа која се оспорава жалбом.");
        zalbaNaOdluku.setNapomene(napomene);

        String id = UUID.randomUUID().toString();
        zalbaNaOdluku.setAbout("http://localhost:8081/complaint/resolution/" + id);
        trazilac.setRel("pred:potrazuje");
        trazilac.setHref("http://localhost:8081/korisnik/" + userDetails.getId());



        zalbaNaOdlukuRepository.save(id, zalbaNaOdluku);
    }

    public ZalbaNaOdluku getOne (String id) throws Exception {
        return zalbaNaOdlukuRepository.getOne(id);
    }

    public StringWriter generateHTML(String id) throws FileNotFoundException {
        return this.generateHTML(id, zalbaNaOdlukuRepository);
    }

    public String[] getList() throws XMLDBException, IllegalAccessException, InstantiationException {
        return zalbaNaOdlukuRepository.listComplaints();
    }

    public ByteArrayOutputStream generatePDF(String id) throws IOException, DocumentException {
        return this.generatePDF(id, zalbaNaOdlukuRepository);
    }

    @Override
    public ByteArrayOutputStream getOneRDF(String id) throws Exception {
        return zalbaNaOdlukuRepository.getOneMetadataRDF(id);
    }

    @Override
    public ByteArrayOutputStream getOneJSON(String id) throws Exception {
        return zalbaNaOdlukuRepository.getOneMetadataJSON(id);
    }

    public ComplaintsListDTO searchText(String query) {
        try {
            return zalbaNaOdlukuRepository.searchText(query);
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
        String sparqlQuery = "SELECT * FROM <http://localhost:8080/fuseki/EDataset/data/example/zalbanaodluku/metadata>\n" +
                "WHERE {\n" +
                "  ?subject <http://localhost/predikati/podnosenje> ?datumPodnosenja .\n" +
                "  ?subject <http://localhost/predikati/upucujeSe> ?organKomeSeUpucuje .\n" +
                "  ?subject <http://localhost/predikati/mestoOrgana> ?mestoOrgana .\n" +
                "  ?subject <http://localhost/predikati/drzavaOrgana> ?drzavaOrgana .\n" +
                "  ?subject <http://localhost/predikati/potrazuje> ?trazilac .\n" +
                "  ?subject <http://localhost/predikati/status> ?status .\n" +
                "  FILTER (regex(str(?datumPodnosenja), \"%s\")) .\n" +
                "  FILTER (regex(str(?organKomeSeUpucuje), \"%s\")) .\n" +
                "  FILTER (regex(str(?mestoOrgana), \"%s\")) .\n" +
                "  FILTER (regex(str(?drzavaOrgana), \"%s\")) .\n" +
                "  FILTER (regex(str(?trazilac), \"%s\")) .\n" +
                "  FILTER (regex(str(?status), \"%s\")) .\n" +
                "}\n" +
                "LIMIT 100";

        // NO ESCAPE!
        sparqlQuery = String.format(
                sparqlQuery,
                query.submissionDateRegex,
                query.authorityRegex,
                query.placeRegex,
                query.stateRegex,
                query.applicantRegex,
                "");

        return zalbaNaOdlukuRepository.queryRDF(sparqlQuery);
    }
    public void setPrihvaceno(String id, String status){
        try {
            zalbaNaOdlukuRepository.setPrihvaceno(id, status);
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public List<ZalbaNaOdluku> getAll() {
        System.out.println("iz servisa");
        try {
            return this.zalbaNaOdlukuRepository.getAll();
        } catch (Exception e) {
            return null;
        }
    }
}
