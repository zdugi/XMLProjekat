package com.organ.project_organ.service;

import com.organ.project_organ.model.xml_opste.TTrazilac;
import com.organ.project_organ.model.xml_zahtev.*;
import com.organ.project_organ.model.xml_zahtev.ObjectFactory;
import com.organ.project_organ.pojo.*;
import com.organ.project_organ.repository.impl.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import com.organ.project_organ.model.xml_opste.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class ZahtevService {
    @Autowired
    public ZahtevRepository zahtevRepository;

    public ZahtevService() {
        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();
    }

    public void create(ZahtevDokumentDTO zahtev) throws Exception {
        //TODO check
        //TODO update for GRAPH
        ObjectFactory factory = new ObjectFactory();

        Zahtev newZahtev = factory.createZahtev();

        TOrgan organ = new TOrgan();

        TAdresa adresa = new TAdresa();
        adresa.setBroj(BigInteger.valueOf(Long.valueOf(zahtev.organ.adresa.broj)));

        TAdresa.Drzava drzava = new TAdresa.Drzava();
        drzava.setProperty("pred:drzavaOrgana");
        drzava.setValue(zahtev.organ.adresa.drzava);
        adresa.setDrzava(drzava);

        TAdresa.Mesto mesto = new TAdresa.Mesto();
        mesto.setProperty("pred:mestoOrgana");
        mesto.setValue(zahtev.organ.adresa.mesto);
        adresa.setMesto(mesto);
        adresa.setPostanskiBroj(BigInteger.valueOf(Long.valueOf(zahtev.organ.adresa.postanskiBroj)));
        adresa.setUlica(zahtev.organ.adresa.ulica);

        organ.setAdresa(adresa);

        TOrgan.Naziv naziv = new TOrgan.Naziv();
        naziv.setProperty("pred:upucujeSe");
        naziv.setValue(zahtev.organ.naziv);
        organ.setNaziv(naziv);

        newZahtev.setOrgan(organ);

        TTeloZahteva telo = new TTeloZahteva();

        TParagraf paragraf = new TParagraf();

        TTipoviZahteva tipovi = new TTipoviZahteva();
        TTipZahteva tip1 = new TTipZahteva();
        TTipZahteva tip2 = new TTipZahteva();
        TTipZahteva tip3 = new TTipZahteva();
        TTipZahteva tip4 = new TTipZahteva();

        if (zahtev.zahtevam.opcija.size() != 4)
            throw new Exception("Broj opcija ne odgovara originalnom dokumentu.");

        //TODO check multiple selection
        //TODO add fields for

        tip1.getContent().add("обавештење да ли поседује тражену информацију");
        tip1.setOdabrano(zahtev.zahtevam.opcija.get(0).cekiran);
        tip2.getContent().add("увид у документ који садржи тражену информацију");
        tip2.setOdabrano(zahtev.zahtevam.opcija.get(1).cekiran);
        tip3.getContent().add("копију документа који садржи тражену информацију");
        tip3.setOdabrano(zahtev.zahtevam.opcija.get(2).cekiran);
        tip4.getContent().add("достављање копије документа који садржи тражену информацију");
        tip4.setOdabrano(zahtev.zahtevam.opcija.get(3).cekiran);

        TTipoviDostave tipoviDostave = new TTipoviDostave();

        TTipDostave dostava1 = new TTipDostave();
        TTipDostave dostava2 = new TTipDostave();
        TTipDostave dostava3 = new TTipDostave();
        TTipDostave dostava4 = new TTipDostave();

        dostava1.getContent().add("поштом");
        dostava2.getContent().add("електронском поштом");
        dostava3.getContent().add("факсом");
        dostava4.getContent().add("на други начин");

        // CUSTOM DELIVERY LAST OPTION!!
        List<DostavaDTO> dostavaDTOS = zahtev.zahtevam.opcija.get(zahtev.zahtevam.opcija.size() - 1).dostava;
        String dostavaNaDrugiNacin = dostavaDTOS.get(dostavaDTOS.size() - 1).dodatno;
        JAXBElement<String> dodatniTip = new JAXBElement<String>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Dodatni_tip"), String.class, dostavaNaDrugiNacin);
        dostava4.getContent().add(dodatniTip);

        tipoviDostave.getTipDostave().add(dostava1);
        tipoviDostave.getTipDostave().add(dostava2);
        tipoviDostave.getTipDostave().add(dostava3);
        tipoviDostave.getTipDostave().add(dostava4);

        JAXBElement<TTipoviDostave> jaxbTTipovi = new JAXBElement<TTipoviDostave>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_dostave"), TTipoviDostave.class, tipoviDostave);
        tip4.getContent().add(jaxbTTipovi);

        tipovi.getTipZahteva().add(tip1);
        tipovi.getTipZahteva().add(tip2);
        tipovi.getTipZahteva().add(tip3);
        tipovi.getTipZahteva().add(tip4);

        paragraf.getContent().add("Na osnovu člana 15. st. 1. Zakona o slobodnom pristupu informacijama od javnog značaja\n" +
                "         („Službeni glasnik RS“, br. 120/04, 54/07, 104/09 i 36/10), od gore navedenog organa\n" +
                "         zahtevam:");
        JAXBElement<TTipoviZahteva> jaxbTipovi = new JAXBElement<TTipoviZahteva>(new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_zahteva"), TTipoviZahteva.class, tipovi);
        paragraf.getContent().add(jaxbTipovi);

        telo.setParagraf(paragraf);

        TInformacije informacije = new TInformacije();
        informacije.setNaslov("Овај захтев се односи на следеће информације");
        informacije.setOpis(zahtev.zahtevam.opis.tekst);
        informacije.setSavet("навести што прецизнији опис информације која се тражи као и друге податке који олакшавају проналажење тражене информације");

        telo.setInformacije(informacije);

        // set telo
        newZahtev.setTeloZahteva(telo);

        // set naziv
        newZahtev.setNaziv("за приступ информацији од јавног значаја");

        TDodatneInformacije dodatne = new TDodatneInformacije();
        TDatum datum = new TDatum();

        datum.setValue("[ovde ce se generisati vrednost]");
        datum.setProperty("podnosenje");

        TOsoba osoba = new TOsoba();
        osoba.setIme("[osoba iz sesije]");
        osoba.setPrezime("[osoba iz sesije]");

        TAdresa tAdresa = new TAdresa();
        //todo: pass in form
        tAdresa.setDrzava(drzava);
        tAdresa.setMesto(mesto);
        tAdresa.setBroj(BigInteger.valueOf(7L));
        tAdresa.setPostanskiBroj(BigInteger.valueOf(7L));
        tAdresa.setUlica("Gordana Mackica");

        TTrazilac trazilac = new TTrazilac();
        trazilac.setAdresa(tAdresa);
        trazilac.setKontakt("[uzimam iz sesije]");
        trazilac.setOsoba(osoba);

        dodatne.setDatum(datum);
        //todo: mesto podnosenja property
        dodatne.setMesto(zahtev.mestoPodnosenja.naziv);
        dodatne.setTrazilac(trazilac);

        // set dodatne
        newZahtev.setDodatneInformacije(dodatne);

        // id setup
        String id = UUID.randomUUID().toString();
        newZahtev.setAbout("http://localhost:8081/request/" + id);
        trazilac.setRel("pred:potrazuje");
        trazilac.setHref("http://localhost:8081/user/USER_ID");

        zahtevRepository.save(id, newZahtev);
    }

    public String[] getList() throws XMLDBException, IllegalAccessException, InstantiationException {
        return zahtevRepository.listResources();
    }

    public ByteArrayOutputStream generatePDF(String requestId, String xslPath) throws IOException, DocumentException {
        // Step 1
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Step 2
        PdfWriter writer = PdfWriter.getInstance(document, out);

        // Step 3
        document.open();

        ByteArrayInputStream stream = new ByteArrayInputStream(generateHTML(requestId, xslPath).toString().getBytes());

        // Step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, stream);

        // Step 5
        document.close();

        return out;
    }

    public org.w3c.dom.Document buildDocument(InputStream is) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(is);

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    public StringWriter generateHTML(String requestId, String xslPath) throws FileNotFoundException {

        try {
            StringWriter os = zahtevRepository.getOneXMLStream(requestId);

            StringWriter htmlOutput = new StringWriter();

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(new ByteArrayInputStream(os.toString().getBytes())));
            StreamResult result = new StreamResult(htmlOutput);
            transformer.transform(source, result);

            return htmlOutput;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String INPUT_FILE = "src/main/resources/bookstore.xml";

    public static final String XSL_FILE = "src/main/resources/zahtev_temp.xsl";

    public static final String HTML_FILE = "src/main/resources/bookstore.html";

    public static final String OUTPUT_FILE = "src/main/resources/bookstore.pdf";
}