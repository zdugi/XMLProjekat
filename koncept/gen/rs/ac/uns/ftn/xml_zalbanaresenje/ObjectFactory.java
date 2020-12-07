//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 05:39:38 PM CET 
//


package rs.ac.uns.ftn.xml_zalbanaresenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TIdResenja;
import rs.ac.uns.ftn.xml_opste.TOrgan;
import rs.ac.uns.ftn.xml_opste.TOsoba;
import rs.ac.uns.ftn.xml_opste.TZakon;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xml_zalbanaresenje package. 
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

    private final static QName _TTeloZalbeResenjeZalilacOsoba_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "ZalilacOsoba");
    private final static QName _TTeloZalbeResenjeOpisZalbe_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "OpisZalbe");
    private final static QName _TTeloZalbeResenjeZakon_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "Zakon");
    private final static QName _TTeloZalbeResenjeDatumPodnosenjaZahteva_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "Datum_podnosenja_zahteva");
    private final static QName _TTeloZalbeResenjeZalilacOrgan_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "ZalilacOrgan");
    private final static QName _TTeloZalbeResenjeAdresa_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "Adresa");
    private final static QName _TTeloZalbeResenjeBrojResenja_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "BrojResenja");
    private final static QName _TTeloZalbeResenjeOrganDonosilacOdluke_QNAME = new QName("http://ftn.uns.ac.rs/xml_zalbanaresenje", "OrganDonosilacOdluke");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xml_zalbanaresenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZalbaNaResenje }
     * 
     */
    public ZalbaNaResenje createZalbaNaResenje() {
        return new ZalbaNaResenje();
    }

    /**
     * Create an instance of {@link TTeloZalbeResenje }
     * 
     */
    public TTeloZalbeResenje createTTeloZalbeResenje() {
        return new TTeloZalbeResenje();
    }

    /**
     * Create an instance of {@link ZalbaNaResenje.Napomene }
     * 
     */
    public ZalbaNaResenje.Napomene createZalbaNaResenjeNapomene() {
        return new ZalbaNaResenje.Napomene();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOsoba }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "ZalilacOsoba", scope = TTeloZalbeResenje.class)
    public JAXBElement<TOsoba> createTTeloZalbeResenjeZalilacOsoba(TOsoba value) {
        return new JAXBElement<TOsoba>(_TTeloZalbeResenjeZalilacOsoba_QNAME, TOsoba.class, TTeloZalbeResenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "OpisZalbe", scope = TTeloZalbeResenje.class)
    public JAXBElement<String> createTTeloZalbeResenjeOpisZalbe(String value) {
        return new JAXBElement<String>(_TTeloZalbeResenjeOpisZalbe_QNAME, String.class, TTeloZalbeResenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TZakon }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "Zakon", scope = TTeloZalbeResenje.class)
    public JAXBElement<TZakon> createTTeloZalbeResenjeZakon(TZakon value) {
        return new JAXBElement<TZakon>(_TTeloZalbeResenjeZakon_QNAME, TZakon.class, TTeloZalbeResenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDatum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "Datum_podnosenja_zahteva", scope = TTeloZalbeResenje.class)
    public JAXBElement<TDatum> createTTeloZalbeResenjeDatumPodnosenjaZahteva(TDatum value) {
        return new JAXBElement<TDatum>(_TTeloZalbeResenjeDatumPodnosenjaZahteva_QNAME, TDatum.class, TTeloZalbeResenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOrgan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "ZalilacOrgan", scope = TTeloZalbeResenje.class)
    public JAXBElement<TOrgan> createTTeloZalbeResenjeZalilacOrgan(TOrgan value) {
        return new JAXBElement<TOrgan>(_TTeloZalbeResenjeZalilacOrgan_QNAME, TOrgan.class, TTeloZalbeResenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TAdresa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "Adresa", scope = TTeloZalbeResenje.class)
    public JAXBElement<TAdresa> createTTeloZalbeResenjeAdresa(TAdresa value) {
        return new JAXBElement<TAdresa>(_TTeloZalbeResenjeAdresa_QNAME, TAdresa.class, TTeloZalbeResenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TIdResenja }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "BrojResenja", scope = TTeloZalbeResenje.class)
    public JAXBElement<TIdResenja> createTTeloZalbeResenjeBrojResenja(TIdResenja value) {
        return new JAXBElement<TIdResenja>(_TTeloZalbeResenjeBrojResenja_QNAME, TIdResenja.class, TTeloZalbeResenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOrgan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", name = "OrganDonosilacOdluke", scope = TTeloZalbeResenje.class)
    public JAXBElement<TOrgan> createTTeloZalbeResenjeOrganDonosilacOdluke(TOrgan value) {
        return new JAXBElement<TOrgan>(_TTeloZalbeResenjeOrganDonosilacOdluke_QNAME, TOrgan.class, TTeloZalbeResenje.class, value);
    }

}
