//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 03:25:41 PM CET 
//


package rs.ac.uns.ftn.xml_zalba_na_cutanje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TOrgan;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xml_zalba_na_cutanje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TTeloZalbeDatumPodnosenjaZahteva_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Datum_podnosenja_zahteva");
    private final static QName _TTeloZalbePodaciOZahtevuIInformacija_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Podaci_o_zahtevu_i_informacija");
    private final static QName _TTeloZalbeNapomena_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Napomena");
    private final static QName _TTeloZalbeRazloziZalbe_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Razlozi_zalbe");
    private final static QName _TTeloZalbeOrgan_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalba_na_cutanje", "Organ");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xml_zalba_na_cutanje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TRazloziZalbe }
     * 
     */
    public TRazloziZalbe createTRazloziZalbe() {
        return new TRazloziZalbe();
    }

    /**
     * Create an instance of {@link ZalbaNaCutanje }
     * 
     */
    public ZalbaNaCutanje createZalbaNaCutanje() {
        return new ZalbaNaCutanje();
    }

    /**
     * Create an instance of {@link TTeloZalbe }
     * 
     */
    public TTeloZalbe createTTeloZalbe() {
        return new TTeloZalbe();
    }

    /**
     * Create an instance of {@link TRazloziZalbe.RazlogZalbe }
     * 
     */
    public TRazloziZalbe.RazlogZalbe createTRazloziZalbeRazlogZalbe() {
        return new TRazloziZalbe.RazlogZalbe();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDatum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", name = "Datum_podnosenja_zahteva", scope = TTeloZalbe.class)
    public JAXBElement<TDatum> createTTeloZalbeDatumPodnosenjaZahteva(TDatum value) {
        return new JAXBElement<TDatum>(_TTeloZalbeDatumPodnosenjaZahteva_QNAME, TDatum.class, TTeloZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", name = "Podaci_o_zahtevu_i_informacija", scope = TTeloZalbe.class)
    public JAXBElement<String> createTTeloZalbePodaciOZahtevuIInformacija(String value) {
        return new JAXBElement<String>(_TTeloZalbePodaciOZahtevuIInformacija_QNAME, String.class, TTeloZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", name = "Napomena", scope = TTeloZalbe.class)
    public JAXBElement<String> createTTeloZalbeNapomena(String value) {
        return new JAXBElement<String>(_TTeloZalbeNapomena_QNAME, String.class, TTeloZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRazloziZalbe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", name = "Razlozi_zalbe", scope = TTeloZalbe.class)
    public JAXBElement<TRazloziZalbe> createTTeloZalbeRazloziZalbe(TRazloziZalbe value) {
        return new JAXBElement<TRazloziZalbe>(_TTeloZalbeRazloziZalbe_QNAME, TRazloziZalbe.class, TTeloZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOrgan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", name = "Organ", scope = TTeloZalbe.class)
    public JAXBElement<TOrgan> createTTeloZalbeOrgan(TOrgan value) {
        return new JAXBElement<TOrgan>(_TTeloZalbeOrgan_QNAME, TOrgan.class, TTeloZalbe.class, value);
    }

}
