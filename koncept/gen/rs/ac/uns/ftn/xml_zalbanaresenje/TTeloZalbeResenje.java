//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.06 at 05:25:14 PM CET 
//


package rs.ac.uns.ftn.xml_zalbanaresenje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TIdResenja;
import rs.ac.uns.ftn.xml_opste.TOrgan;
import rs.ac.uns.ftn.xml_opste.TOsoba;
import rs.ac.uns.ftn.xml_opste.TZakon;


/**
 * <p>Java class for TTelo_Zalbe_Resenje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TTelo_Zalbe_Resenje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;element name="ZalilacOrgan" type="{http://ftn.uns.ac.rs/xml_opste}TOrgan"/>
 *             &lt;element name="ZalilacOsoba" type="{http://ftn.uns.ac.rs/xml_opste}TOsoba"/>
 *           &lt;/choice>
 *           &lt;element name="Adresa" type="{http://ftn.uns.ac.rs/xml_opste}TAdresa"/>
 *         &lt;/sequence>
 *         &lt;element name="OrganDonosilacOdluke" type="{http://ftn.uns.ac.rs/xml_opste}TOrgan"/>
 *         &lt;element name="BrojResenja" type="{http://ftn.uns.ac.rs/xml_opste}TIdResenja"/>
 *         &lt;element name="Datum_podnosenja_zahteva" type="{http://ftn.uns.ac.rs/xml_opste}TDatum"/>
 *         &lt;element name="OpisZalbe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Zakon" type="{http://ftn.uns.ac.rs/xml_opste}TZakon"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTelo_Zalbe_Resenje", propOrder = {
    "content"
})
public class TTeloZalbeResenje {

    @XmlElementRefs({
        @XmlElementRef(name = "ZalilacOrgan", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class),
        @XmlElementRef(name = "Zakon", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class),
        @XmlElementRef(name = "OpisZalbe", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class),
        @XmlElementRef(name = "OrganDonosilacOdluke", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class),
        @XmlElementRef(name = "ZalilacOsoba", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class),
        @XmlElementRef(name = "BrojResenja", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class),
        @XmlElementRef(name = "Adresa", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class),
        @XmlElementRef(name = "Datum_podnosenja_zahteva", namespace = "http://ftn.uns.ac.rs/xml_zalbanaresenje", type = JAXBElement.class)
    })
    @XmlMixed
    protected List<Serializable> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TOrgan }{@code >}
     * {@link JAXBElement }{@code <}{@link TZakon }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TOrgan }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link TOsoba }{@code >}
     * {@link JAXBElement }{@code <}{@link TIdResenja }{@code >}
     * {@link JAXBElement }{@code <}{@link TAdresa }{@code >}
     * {@link JAXBElement }{@code <}{@link TDatum }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

}
