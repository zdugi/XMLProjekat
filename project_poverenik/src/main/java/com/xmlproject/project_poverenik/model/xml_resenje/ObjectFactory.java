//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.02 at 05:25:05 PM CET 
//


package com.xmlproject.project_poverenik.model.xml_resenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.xmlproject.project_poverenik.model.xml_opste.TDatum;
import com.xmlproject.project_poverenik.model.xml_opste.TOrgan;
import com.xmlproject.project_poverenik.model.xml_opste.TOsoba;
import com.xmlproject.project_poverenik.model.xml_opste.TZakon;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xml_resenje package. 
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

    private final static QName _TUvodZalilacOrgan_QNAME = new QName("http://ftn.uns.ac.rs/xml_resenje", "ZalilacOrgan");
    private final static QName _TUvodZalilacOsoba_QNAME = new QName("http://ftn.uns.ac.rs/xml_resenje", "ZalilacOsoba");
    private final static QName _TUvodZakon_QNAME = new QName("http://ftn.uns.ac.rs/xml_resenje", "Zakon");
    private final static QName _TUvodDatumPodnosenja_QNAME = new QName("http://ftn.uns.ac.rs/xml_resenje", "DatumPodnosenja");
    private final static QName _TUvodOrgan_QNAME = new QName("http://ftn.uns.ac.rs/xml_resenje", "Organ");
    private final static QName _TSekcijaSekcija_QNAME = new QName("http://ftn.uns.ac.rs/xml_resenje", "sekcija");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xml_resenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Resenje }
     * 
     */
    public Resenje createResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link TZalba }
     * 
     */
    public TZalba createTZalba() {
        return new TZalba();
    }

    /**
     * Create an instance of {@link TUvod }
     * 
     */
    public TUvod createTUvod() {
        return new TUvod();
    }

    /**
     * Create an instance of {@link Resenje.ResenjeUkratko }
     * 
     */
    public Resenje.ResenjeUkratko createResenjeResenjeUkratko() {
        return new Resenje.ResenjeUkratko();
    }

    /**
     * Create an instance of {@link TSekcija }
     * 
     */
    public TSekcija createTSekcija() {
        return new TSekcija();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOrgan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_resenje", name = "ZalilacOrgan", scope = TUvod.class)
    public JAXBElement<TOrgan> createTUvodZalilacOrgan(TOrgan value) {
        return new JAXBElement<TOrgan>(_TUvodZalilacOrgan_QNAME, TOrgan.class, TUvod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOsoba }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_resenje", name = "ZalilacOsoba", scope = TUvod.class)
    public JAXBElement<TOsoba> createTUvodZalilacOsoba(TOsoba value) {
        return new JAXBElement<TOsoba>(_TUvodZalilacOsoba_QNAME, TOsoba.class, TUvod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TZakon }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_resenje", name = "Zakon", scope = TUvod.class)
    public JAXBElement<TZakon> createTUvodZakon(TZakon value) {
        return new JAXBElement<TZakon>(_TUvodZakon_QNAME, TZakon.class, TUvod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDatum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_resenje", name = "DatumPodnosenja", scope = TUvod.class)
    public JAXBElement<TDatum> createTUvodDatumPodnosenja(TDatum value) {
        return new JAXBElement<TDatum>(_TUvodDatumPodnosenja_QNAME, TDatum.class, TUvod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOrgan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_resenje", name = "Organ", scope = TUvod.class)
    public JAXBElement<TOrgan> createTUvodOrgan(TOrgan value) {
        return new JAXBElement<TOrgan>(_TUvodOrgan_QNAME, TOrgan.class, TUvod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TSekcija }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_resenje", name = "sekcija", scope = TSekcija.class)
    public JAXBElement<TSekcija> createTSekcijaSekcija(TSekcija value) {
        return new JAXBElement<TSekcija>(_TSekcijaSekcija_QNAME, TSekcija.class, TSekcija.class, value);
    }

}
