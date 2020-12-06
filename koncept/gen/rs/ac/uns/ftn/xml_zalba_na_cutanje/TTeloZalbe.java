//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.06 at 07:35:21 PM CET 
//


package rs.ac.uns.ftn.xml_zalba_na_cutanje;

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
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TOrgan;


/**
 * <p>Java class for TTelo_Zalbe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TTelo_Zalbe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Organ" type="{http://ftn.uns.ac.rs/xml_opste}TOrgan"/>
 *         &lt;element name="Razlozi_zalbe" type="{http://ftn.uns.ac.rs/xml_zalba_na_cutanje}TRazlozi_zalbe"/>
 *         &lt;element name="Podaci_o_zahtevu_i_informacija" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Napomena" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Datum_podnosenja_zahteva" type="{http://ftn.uns.ac.rs/xml_opste}TDatum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTelo_Zalbe", propOrder = {
    "content"
})
public class TTeloZalbe {

    @XmlElementRefs({
        @XmlElementRef(name = "Napomena", namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", type = JAXBElement.class),
        @XmlElementRef(name = "Podaci_o_zahtevu_i_informacija", namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", type = JAXBElement.class),
        @XmlElementRef(name = "Organ", namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", type = JAXBElement.class),
        @XmlElementRef(name = "Razlozi_zalbe", namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", type = JAXBElement.class),
        @XmlElementRef(name = "Datum_podnosenja_zahteva", namespace = "http://ftn.uns.ac.rs/xml_zalba_na_cutanje", type = JAXBElement.class)
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
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TRazloziZalbe }{@code >}
     * {@link String }
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
