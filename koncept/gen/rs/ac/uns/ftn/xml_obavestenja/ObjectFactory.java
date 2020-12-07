//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 05:39:38 PM CET 
//


package rs.ac.uns.ftn.xml_obavestenja;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TZakon;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xml_obavestenja package. 
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

    private final static QName _TTeloObavestenjaOpis_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Opis");
    private final static QName _TTeloObavestenjaPocetniSat_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Pocetni_sat");
    private final static QName _TTeloObavestenjaSati_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Sati");
    private final static QName _TTeloObavestenjaAdresa_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Adresa");
    private final static QName _TTeloObavestenjaZakon_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Zakon");
    private final static QName _TTeloObavestenjaGodina_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Godina");
    private final static QName _TTeloObavestenjaDan_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Dan");
    private final static QName _TTeloObavestenjaZavrsniSat_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Zavrsni_sat");
    private final static QName _TTeloObavestenjaKancelarija_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Kancelarija");
    private final static QName _TTeloObavestenjaSuma_QNAME = new QName("http://ftn.uns.ac.rs/xml_obavestenja", "Suma");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xml_obavestenja
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TTeloObavestenja }
     * 
     */
    public TTeloObavestenja createTTeloObavestenja() {
        return new TTeloObavestenja();
    }

    /**
     * Create an instance of {@link Obavestenje }
     * 
     */
    public Obavestenje createObavestenje() {
        return new Obavestenje();
    }

    /**
     * Create an instance of {@link TPodnosilac }
     * 
     */
    public TPodnosilac createTPodnosilac() {
        return new TPodnosilac();
    }

    /**
     * Create an instance of {@link TDostavljeno }
     * 
     */
    public TDostavljeno createTDostavljeno() {
        return new TDostavljeno();
    }

    /**
     * Create an instance of {@link TTeloObavestenja.Opis }
     * 
     */
    public TTeloObavestenja.Opis createTTeloObavestenjaOpis() {
        return new TTeloObavestenja.Opis();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TTeloObavestenja.Opis }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Opis", scope = TTeloObavestenja.class)
    public JAXBElement<TTeloObavestenja.Opis> createTTeloObavestenjaOpis(TTeloObavestenja.Opis value) {
        return new JAXBElement<TTeloObavestenja.Opis>(_TTeloObavestenjaOpis_QNAME, TTeloObavestenja.Opis.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Pocetni_sat", scope = TTeloObavestenja.class)
    public JAXBElement<Integer> createTTeloObavestenjaPocetniSat(Integer value) {
        return new JAXBElement<Integer>(_TTeloObavestenjaPocetniSat_QNAME, Integer.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Sati", scope = TTeloObavestenja.class)
    public JAXBElement<BigInteger> createTTeloObavestenjaSati(BigInteger value) {
        return new JAXBElement<BigInteger>(_TTeloObavestenjaSati_QNAME, BigInteger.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TAdresa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Adresa", scope = TTeloObavestenja.class)
    public JAXBElement<TAdresa> createTTeloObavestenjaAdresa(TAdresa value) {
        return new JAXBElement<TAdresa>(_TTeloObavestenjaAdresa_QNAME, TAdresa.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TZakon }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Zakon", scope = TTeloObavestenja.class)
    public JAXBElement<TZakon> createTTeloObavestenjaZakon(TZakon value) {
        return new JAXBElement<TZakon>(_TTeloObavestenjaZakon_QNAME, TZakon.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Godina", scope = TTeloObavestenja.class)
    public JAXBElement<BigInteger> createTTeloObavestenjaGodina(BigInteger value) {
        return new JAXBElement<BigInteger>(_TTeloObavestenjaGodina_QNAME, BigInteger.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Dan", scope = TTeloObavestenja.class)
    public JAXBElement<Integer> createTTeloObavestenjaDan(Integer value) {
        return new JAXBElement<Integer>(_TTeloObavestenjaDan_QNAME, Integer.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Zavrsni_sat", scope = TTeloObavestenja.class)
    public JAXBElement<Integer> createTTeloObavestenjaZavrsniSat(Integer value) {
        return new JAXBElement<Integer>(_TTeloObavestenjaZavrsniSat_QNAME, Integer.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Kancelarija", scope = TTeloObavestenja.class)
    public JAXBElement<BigInteger> createTTeloObavestenjaKancelarija(BigInteger value) {
        return new JAXBElement<BigInteger>(_TTeloObavestenjaKancelarija_QNAME, BigInteger.class, TTeloObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_obavestenja", name = "Suma", scope = TTeloObavestenja.class)
    public JAXBElement<Double> createTTeloObavestenjaSuma(Double value) {
        return new JAXBElement<Double>(_TTeloObavestenjaSuma_QNAME, Double.class, TTeloObavestenja.class, value);
    }

}
