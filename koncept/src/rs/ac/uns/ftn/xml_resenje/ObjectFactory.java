//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.05 at 02:12:09 PM CET 
//


package rs.ac.uns.ftn.xml_resenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


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
     * Create an instance of {@link Zalba }
     * 
     */
    public Zalba createZalba() {
        return new Zalba();
    }

    /**
     * Create an instance of {@link TSekcija }
     * 
     */
    public TSekcija createTSekcija() {
        return new TSekcija();
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
