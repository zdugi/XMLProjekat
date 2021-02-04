package com.xmlproject.project_poverenik.service;

import com.itextpdf.text.DocumentException;
import com.xmlproject.project_poverenik.model.xml_korisnik.Korisnik;
import com.xmlproject.project_poverenik.model.xml_opste.*;
import com.xmlproject.project_poverenik.model.xml_resenje.Resenje;
import com.xmlproject.project_poverenik.model.xml_resenje.ObjectFactory;
import com.xmlproject.project_poverenik.model.xml_resenje.TUvod;
import com.xmlproject.project_poverenik.model.xml_resenje.TZalba;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.TRazloziZalbe;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.TTeloZalbe;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.xmlproject.project_poverenik.repository.ResenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import pojo.ComplaintsAdvanceSearchQuery;
import pojo.ComplaintsListDTO;
import pojo.ResenjeDTO;
import pojo.ResolutionsAdvanceSearchQuery;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ResenjeService extends AbsService {
    @Autowired
    private ResenjeRepository resenjeRepository;

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;

    @Autowired
    private ZalbaNaOdlukuService zalbaNaOdlukuService;

    public ResenjeService() {
        //Repository repository, String xslPath, String fontPath
        super("src/main/resources/resenje_temp.xsl", "src/main/resources/FreeSans.ttf");
    }


    public void create(ResenjeDTO resenjeDTO) throws Exception {
        String idZalbe = resenjeDTO.id_zalbe;
        System.out.println(idZalbe);
        ZalbaNaCutanje zalbaNaCutanje;
        ZalbaNaOdluku zalbaNaOdluku;
        try {
            zalbaNaCutanje = zalbaNaCutanjeService.getOne(idZalbe);
        } catch (Exception e) {
            zalbaNaCutanje = null;
        }
        try {
            zalbaNaOdluku = zalbaNaOdlukuService.getOne(idZalbe);
        } catch (Exception e) {
            zalbaNaOdluku = null;
        }

        if (zalbaNaCutanje == null && zalbaNaOdluku == null) {
            throw new Exception();

        }
        System.out.println(zalbaNaCutanje + " " + zalbaNaOdluku);

        String prihvacena = resenjeDTO.prihvacena;

        if (zalbaNaCutanje != null) {
            //zalbaNaCutanje.setPrihvacena(prihvacena);
            zalbaNaCutanjeService.setPrihvaceno(idZalbe, prihvacena);
            // ovo treba sacuvati
            //<xupdate:element name="zalbaNaCutanje">
            //<xupdate:attribute name="prihvacena">true</xupdate:attribute>
            //</xupdate:element>
        }
        else {
            zalbaNaOdlukuService.setPrihvaceno(idZalbe, prihvacena);
        }

        ObjectFactory factory = new ObjectFactory();

        Resenje resenje = factory.createResenje();

        TDatum datumResenja = new TDatum();
        datumResenja.setProperty("pred:doneseno");
        datumResenja.setValue("danasnji datum");
        resenje.setDatum(datumResenja);

        // na resnje property jel odobrava ili ne
        // set na zalbu jel odobrena ili ne
        Resenje.ZalbaPrihvacena zalbaPrihvacena = new Resenje.ZalbaPrihvacena();
        zalbaPrihvacena.setProperty("pred:prihvacena");
        zalbaPrihvacena.setValue(resenjeDTO.prihvacena);
        resenje.setZalbaPrihvacena(zalbaPrihvacena);


        TZalba zalba = new TZalba();
        zalba.setDatum(null);
        zalba.setBroj(idZalbe);
        resenje.setZalba(zalba);

        // zalbu da setujem status
        Resenje.ResenjeUkratko resenjeUkratko = new Resenje.ResenjeUkratko();
        //resenjeUkratko.setValue("pred:prihvaceno");
        //resenjeUkratko.setPrihvaceno();
        //resenjeUkratko.setPrihvaceno(prihvacena);
        resenjeUkratko.setValue(resenjeDTO.resenje_ukratko);
        resenje.setResenjeUkratko(resenjeUkratko);
        resenje.setObrazlozenje(resenjeDTO.obrazlozenje);

        TUvod tUvod = new TUvod();
        tUvod.getContent().add("Повереник за информације од јавног значаја и заштиту података о личности, у поступку по алби\n" +
                "коју је изјавио");
        TOsoba zalilac = new TOsoba();
        TOrgan organDonosilacOdluke = new TOrgan();
        TDatum datumPodnosenjaZalbe;
        if (zalbaNaCutanje == null){
            datumPodnosenjaZalbe = zalbaNaOdluku.getDodatneInformacije().getDatum();
        }
        else {
            datumPodnosenjaZalbe = zalbaNaCutanje.getDodatneInformacije().getDatum();
        }

        //zalilac.setIme(zalbaNaOdluku.getTeloZalbeNaOdluku().getContent())
        List<Serializable> content;
        if (zalbaNaOdluku != null) {
            content = zalbaNaOdluku.getTeloZalbeNaOdluku().getContent();
        } else {
            content = zalbaNaCutanje.getTeloZalbe().getContent();
        }
        for (Serializable s : content) {
            try {
                JAXBElement<?> ser = (JAXBElement<?>) s;
                String tagFullName = ser.getName().toString();
                String tagName = tagFullName.substring(tagFullName.indexOf("}") + 1);
                if (tagName.equals("ZalilacOsoba")) {
                    zalilac = (TOsoba) ser.getValue();
                    System.out.println("Zalilac: " + zalilac.getIme() + " " + zalilac.getPrezime());
                } else if (tagName.equals("Adresa")) {
                    TAdresa adresaZalioca = (TAdresa) ser.getValue();
                    System.out.println("Adresa zalioca: " + adresaZalioca.getMesto());
                } else if (tagName.equals("OrganDonosilacOdluke")) {
                    organDonosilacOdluke = (TOrgan) ser.getValue();
                    System.out.println("Organ donosilac odluke: " + organDonosilacOdluke.getNaziv());
                } else if (zalbaNaCutanje != null && tagName.equals("Organ")){
                    organDonosilacOdluke = (TOrgan) ser.getValue();
                }
                else {
                    continue;
                }
            } catch (Exception e) {
                try {
                    String str = (String) s;
                    //System.out.println("Tekst: " +  str);
                } catch (Exception ex) {

                }

            }
        }
        if (zalbaNaCutanje != null) {
            zalilac = zalbaNaCutanje.getDodatneInformacije().getTrazilac().getOsoba();
        }


        JAXBElement<TOsoba> zalilacOsobaJ = new JAXBElement<TOsoba>(new QName("http://ftn.uns.ac.rs/xml_resenje", "ZalilacOsoba"), TOsoba.class, zalilac);
        tUvod.getContent().add(zalilacOsobaJ);
        //tUvod.getContent().add("због недобијања тражених информација по његовом захтеву за приступ информацијама од јавног значаја поднетом");
        tUvod.getContent().add(" против ");
        // zbog nepostupanja ako je zalba na cutanje
        JAXBElement<TOrgan> organDonosilacOdlukeJ = new JAXBElement<TOrgan>(new QName("http://ftn.uns.ac.rs/xml_resenje", "Organ"), TOrgan.class, organDonosilacOdluke);


        tUvod.getContent().add(organDonosilacOdlukeJ);
        // broj zalbe, datum zalbe
        if (zalbaNaCutanje != null) {
            tUvod.getContent().add("због недобијања тражених информација по његовом захтеву за приступ информацијама од јавног значаја");
        }
        else {
            tUvod.getContent().add("због непоступања по његовом захтеву за приступ информацијама од јавног значаја");
        }
                // mozda i ovo sa fronta da se posalje
        TDatum datumPodnosenjaZahteva = new TDatum();
        datumPodnosenjaZahteva.setProperty("pred:datumPodnosenjaZahteva");
        datumPodnosenjaZahteva.setValue("datum podnosenja zahteva, to mi treba iz soapa");
        JAXBElement<TDatum> datumPodnosenjaZahtevaJ = new JAXBElement<TDatum>(new QName("http://ftn.uns.ac.rs/xml_resenje", "DatumPodnosenja"), TDatum.class, datumPodnosenjaZahteva);
        tUvod.getContent().add(datumPodnosenjaZahtevaJ);
        //tUvod.getContent().add("datum podnosenja zahteva");             // ovo mi treba preko soapa
        tUvod.getContent().add("на основу члана");
        TZakon zakon1 = new TZakon();
        zakon1.setNazivZakona("Закона о слободном " +
                "приступу информацијама од јавног значаја („Службени гласник РС” бр. 120/04, 54/07, 104/09 и " +
                "36/10)");
        zakon1.getClanZakona().add("члана 35. став 1. тачка 5.");
        JAXBElement<TZakon> zakonJ = new JAXBElement<TZakon>(new QName("http://ftn.uns.ac.rs/xml_resenje", "Zakon"), TZakon.class, zakon1);
        tUvod.getContent().add(zakonJ);
        tUvod.getContent().add(" и ");

        TZakon zakon2 = new TZakon();
        zakon2.setNazivZakona("Закона о заштити података о личности („Службени гласник РС” бр. 87/18)");
        zakon2.getClanZakona().add("чланом 4. тачка 22.");
        zakonJ = new JAXBElement<TZakon>(new QName("http://ftn.uns.ac.rs/xml_resenje", "Zakon"),  TZakon.class, zakon2);
        tUvod.getContent().add(zakonJ);
        TZakon zakon3 = new TZakon();
        zakon3.setNazivZakona("Закона о слободном приступу информацијама од јавног значаја");
        zakon3.getClanZakona().add(" члана 23.");
        zakon3.getClanZakona().add(" и члана 24. став 1.");
        zakonJ = new JAXBElement<TZakon>(new QName("http://ftn.uns.ac.rs/xml_resenje", "Zakon"),  TZakon.class, zakon3);

        tUvod.getContent().add(zakonJ);
        TZakon zakon4 = new TZakon();
        zakon4.setNazivZakona("Закона о општем управном поступку („Службени гласник РС”, бр. 18/16 и 95/18)");
        zakon4.getClanZakona().add("члана 170. став 1. тачка 1.");
        zakonJ = new JAXBElement<TZakon>(new QName("http://ftn.uns.ac.rs/xml_resenje", "Zakon"),  TZakon.class, zakon4);

        tUvod.getContent().add(zakonJ);
        tUvod.getContent().add(", доноси");

        // resenje i obrazlozenje

        resenje.setUvod(tUvod);

        // poverenika uzimam iz sesije
        TOsoba poverenik = new TOsoba();
        poverenik.setIme("Poverenko");
        poverenik.setPrezime("Poverenic");
        resenje.setPoverenik(poverenik);

        // trazilac

        TDatum datumZ = new TDatum();

        // zalilac?
        Korisnik userDetails = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        zalilac.setRel("pred:zalilac");
        zalilac.setHref("http://localhost:8081/user/" + userDetails.getId());

        // id setup
        String id = UUID.randomUUID().toString();
        resenje.setAbout("http://localhost:8081/solution/" + id);
        //trazilac.setRel("pred:potrazuje");
        //trazilac.setHref("http://localhost:8081/solution/" + id);
        resenje.setId(id);
        resenje.setRel("pred:resenjeZa");
        String res = "";
        if (zalbaNaOdluku != null) {
            res = "resolution/";
        }
        resenje.setHref("http://localhost:8081/complaint/" + res + idZalbe);


        resenjeRepository.save(id, resenje);


    }

    public Resenje getOne(String id) throws Exception {
        return resenjeRepository.getOne(id);
    }

    public StringWriter generateHTML(String id) throws FileNotFoundException {
        return this.generateHTML(id, resenjeRepository);
    }

    public String[] getList() throws XMLDBException, IllegalAccessException, InstantiationException {
        return resenjeRepository.listComplaints();
    }

    public ByteArrayOutputStream generatePDF(String id) throws IOException, DocumentException {
        return this.generatePDF(id, resenjeRepository);
    }

    @Override
    public ByteArrayOutputStream getOneRDF(String id) throws Exception {
        return resenjeRepository.getOneMetadataRDF(id);
    }

    @Override
    public ByteArrayOutputStream getOneJSON(String id) throws Exception {
        return resenjeRepository.getOneMetadataJSON(id);
    }
    public ComplaintsListDTO searchText(String query) {
        try {
            return resenjeRepository.searchText(query);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (XMLDBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ByteArrayOutputStream queryRDF(ResolutionsAdvanceSearchQuery query) {
        //query.
        String sparqlQuery = "SELECT * FROM <http://localhost:8080/fuseki/EDataset/data/example/resenje/metadata>\n" +
                "WHERE {\n" +
                "  ?subject <http://localhost/predikati/resenjeZa> ?resenjeZa .\n" +
                "  ?subject <http://localhost/predikati/doneseno> ?doneseno .\n" +
                "  ?subject <http://localhost/predikati/prihvacena> ?prihvacena .\n" +
                "  ?subject <http://localhost/predikati/zalilac> ?zalilac .\n" +
                "  ?subject <http://localhost/predikati/upucujeSe> ?upucujeSe .\n" +
                "  ?subject <http://localhost/predikati/datumPodnosenjaZahteva> ?datumPodnosenjaZahteva .\n" +
                "  FILTER (regex(str(?resenjeZa), \"%s\")) .\n" +
                "  FILTER (regex(str(?doneseno), \"%s\")) .\n" +
                "  FILTER (regex(str(?prihvacena), \"%s\")) .\n" +
                "  FILTER (regex(str(?zalilac), \"%s\")) .\n" +
                "  FILTER (regex(str(?upucujeSe), \"%s\")) .\n" +
                "  FILTER (regex(str(?datumPodnosenjaZahteva), \"%s\")) .\n" +
                "}\n" +
                "LIMIT 100";

        // NO ESCAPE!
        sparqlQuery = String.format(
                sparqlQuery,
                query.resenjeZa,
                query.doneseno,
                query.prihvacena,
                query.zalilac,
                query.upucujeSe,
                query.datumPodnosenjaZahteva);

        return resenjeRepository.queryRDF(sparqlQuery);
    }

}
