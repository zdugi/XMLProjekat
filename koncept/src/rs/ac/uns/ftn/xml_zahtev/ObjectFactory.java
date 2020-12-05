//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.05 at 02:12:09 PM CET 
//


package rs.ac.uns.ftn.xml_zahtev;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xml_zahtev package. 
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

    private final static QName _TTipZahtevaTipoviDostave_QNAME = new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_dostave");
    private final static QName _TTipDostaveDodatniTip_QNAME = new QName("http://ftn.uns.ac.rs/xml_zahtev", "Dodatni_tip");
    private final static QName _TParagrafTipoviZahteva_QNAME = new QName("http://ftn.uns.ac.rs/xml_zahtev", "Tipovi_zahteva");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xml_zahtev
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Zahtev }
     * 
     */
    public Zahtev createZahtev() {
        return new Zahtev();
    }

    /**
     * Create an instance of {@link TTeloZahteva }
     * 
     */
    public TTeloZahteva createTTeloZahteva() {
        return new TTeloZahteva();
    }

    /**
     * Create an instance of {@link TTipoviDostave }
     * 
     */
    public TTipoviDostave createTTipoviDostave() {
        return new TTipoviDostave();
    }

    /**
     * Create an instance of {@link TTipDostave }
     * 
     */
    public TTipDostave createTTipDostave() {
        return new TTipDostave();
    }

    /**
     * Create an instance of {@link TInformacije }
     * 
     */
    public TInformacije createTInformacije() {
        return new TInformacije();
    }

    /**
     * Create an instance of {@link TTipoviZahteva }
     * 
     */
    public TTipoviZahteva createTTipoviZahteva() {
        return new TTipoviZahteva();
    }

    /**
     * Create an instance of {@link TTipZahteva }
     * 
     */
    public TTipZahteva createTTipZahteva() {
        return new TTipZahteva();
    }

    /**
     * Create an instance of {@link TParagraf }
     * 
     */
    public TParagraf createTParagraf() {
        return new TParagraf();
    }

    /**
     * Create an instance of {@link TTrazilac }
     * 
     */
    public TTrazilac createTTrazilac() {
        return new TTrazilac();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TTipoviDostave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zahtev", name = "Tipovi_dostave", scope = TTipZahteva.class)
    public JAXBElement<TTipoviDostave> createTTipZahtevaTipoviDostave(TTipoviDostave value) {
        return new JAXBElement<TTipoviDostave>(_TTipZahtevaTipoviDostave_QNAME, TTipoviDostave.class, TTipZahteva.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zahtev", name = "Dodatni_tip", scope = TTipDostave.class)
    public JAXBElement<String> createTTipDostaveDodatniTip(String value) {
        return new JAXBElement<String>(_TTipDostaveDodatniTip_QNAME, String.class, TTipDostave.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TTipoviZahteva }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/xml_zahtev", name = "Tipovi_zahteva", scope = TParagraf.class)
    public JAXBElement<TTipoviZahteva> createTParagrafTipoviZahteva(TTipoviZahteva value) {
        return new JAXBElement<TTipoviZahteva>(_TParagrafTipoviZahteva_QNAME, TTipoviZahteva.class, TParagraf.class, value);
    }

}
