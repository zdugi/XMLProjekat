package com.xmlproject.project_poverenik.service;

import com.itextpdf.text.DocumentException;
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
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import pojo.ResenjeDTO;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.util.Date;
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
        super("src/main/resources/resenje_temp.xsl","src/main/resources/FreeSans.ttf");
    }



    public void create(ResenjeDTO resenjeDTO) throws Exception {
        String idZalbe = resenjeDTO.id_zalbe;
        System.out.println(idZalbe);
        ZalbaNaCutanje zalbaNaCutanje;
        ZalbaNaOdluku zalbaNaOdluku;
        try {
            zalbaNaCutanje = zalbaNaCutanjeService.getOne(idZalbe);
        }
        catch(Exception e){
            zalbaNaCutanje = null;
        }
        try {
            zalbaNaOdluku = zalbaNaOdlukuService.getOne(idZalbe);
        }
        catch(Exception e){
            zalbaNaOdluku = null;
        }

        if (zalbaNaCutanje == null && zalbaNaOdluku == null) {
            throw new Exception();

        }
        System.out.println(zalbaNaCutanje +  " " + zalbaNaOdluku);

        ObjectFactory factory = new ObjectFactory();

        Resenje resenje = factory.createResenje();

        TZalba zalba = new TZalba();
        zalba.setDatum(null);
        zalba.setBroj(idZalbe);
        resenje.setZalba(zalba);

        TDatum tdatum11 = new TDatum();
        tdatum11.setValue(new Date().toString());
        resenje.setDatum(tdatum11);

        // zalbu da setujem status
        Resenje.ResenjeUkratko resenjeUkratko = new Resenje.ResenjeUkratko();
        resenjeUkratko.setValue(resenjeDTO.resenje_ukratko);
        resenje.setResenjeUkratko(resenjeUkratko);
        resenje.setObrazlozenje(resenjeDTO.obrazlozenje);

        TUvod tUvod = new TUvod();
        tUvod.getContent().add("Повереник за информације од јавног значаја и заштиту података о личности, у поступку по алби\n" +
                "коју је изјавио");
        TOsoba zalilac = new TOsoba();
        TOrgan organDonosilacOdluke = new TOrgan();
        TDatum datumPodnosenjaZalbe = zalbaNaOdluku.getDodatneInformacije().getDatum();

        //zalilac.setIme(zalbaNaOdluku.getTeloZalbeNaOdluku().getContent())
        for (Serializable s: zalbaNaOdluku.getTeloZalbeNaOdluku().getContent()) {
            try {
                JAXBElement<?> ser = (JAXBElement<?>)s;
                String tagFullName = ser.getName().toString();
                String tagName = tagFullName.substring(tagFullName.indexOf("}") + 1);
                if (tagName.equals("ZalilacOsoba")) {
                    zalilac = (TOsoba) ser.getValue();
                    System.out.println("Zalilac: " + zalilac.getIme() + " " + zalilac.getPrezime());
                }
                else if (tagName.equals("Adresa")) {
                    TAdresa adresaZalioca = (TAdresa)ser.getValue();
                    System.out.println("Adresa zalioca: " + adresaZalioca.getMesto());
                }
                else if (tagName.equals("OrganDonosilacOdluke")) {
                    organDonosilacOdluke = (TOrgan)ser.getValue();
                    System.out.println("Organ donosilac odluke: " + organDonosilacOdluke.getNaziv());
                }

                else {
                    continue;
                }
            }
            catch(Exception e) {
                try {
                    String str = (String)s;
                    //System.out.println("Tekst: " +  str);
                }
                catch(Exception ex) {

                }

            }
        }
        JAXBElement<TOsoba> zalilacOsobaJ = new JAXBElement<TOsoba>(new QName("http://ftn.uns.ac.rs/xml_resenje", "ZalilacOsoba"), TOsoba.class, zalilac);
        tUvod.getContent().add(zalilacOsobaJ);
        tUvod.getContent().add("против обавештења?непоступања (у зависности од тупа жалабе ћу ово написати");
        JAXBElement<TOrgan> organDonosilacOdlukeJ = new JAXBElement<TOrgan>(new QName("http://ftn.uns.ac.rs/xml_resenje", "Organ"), TOrgan.class, organDonosilacOdluke);

        tUvod.getContent().add(organDonosilacOdlukeJ);
        // broj zalbe, datum zalbe
        tUvod.getContent().add("због недобијања тражених информација по његовом захтеву за приступ информацијама од јавног\n" +
                "значаја");         // mozda i ovo sa fronta da se posalje
        tUvod.getContent().add("datum podnosenja zahteva");
        tUvod.getContent().add("на основу члана");
        // zakoni
        tUvod.getContent().add("доноси");

        // resenje i obrazlozenje

        resenje.setUvod(tUvod);

        // poverenika uzimam iz sesije
        TOsoba poverenik = new TOsoba();
        poverenik.setIme("Poverenko");
        poverenik.setPrezime("Poverenic");

        resenje.setPoverenik(poverenik);

        TDatum datumR = new TDatum();
        datumR.setValue("[ovde ce se generisati vrednost]");
        datumR.setProperty("pred:donosenje");

        resenje.setDatum(datumR);

        TDatum datumZ = new TDatum();




        // id setup
        String id = UUID.randomUUID().toString();
        resenje.setAbout("http://localhost:8081/solution/" + id);
        //trazilac.setRel("pred:potrazuje");
        //trazilac.setHref("http://localhost:8081/solution/" + id);
        resenje.setId(id);


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
        return null;
    }

    @Override
    public ByteArrayOutputStream getOneJSON(String id) throws Exception {
        return null;
    }
}
