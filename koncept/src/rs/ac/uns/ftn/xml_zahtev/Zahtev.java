//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.05 at 02:12:09 PM CET 
//


package rs.ac.uns.ftn.xml_zahtev;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xml_opste.TDodatneInformacije;
import rs.ac.uns.ftn.xml_opste.TOrgan;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Organ" type="{http://ftn.uns.ac.rs/xml_opste}TOrgan"/>
 *         &lt;element name="Telo_zahteva" type="{http://ftn.uns.ac.rs/xml_zahtev}TTelo_zahteva"/>
 *         &lt;element name="Dodatne_informacije" type="{http://ftn.uns.ac.rs/xml_opste}TDodatne_informacije"/>
 *       &lt;/sequence>
 *       &lt;attribute name="naziv" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "organ",
    "teloZahteva",
    "dodatneInformacije"
})
@XmlRootElement(name = "Zahtev")
public class Zahtev {

    @XmlElement(name = "Organ", required = true)
    protected TOrgan organ;
    @XmlElement(name = "Telo_zahteva", required = true)
    protected TTeloZahteva teloZahteva;
    @XmlElement(name = "Dodatne_informacije", required = true)
    protected TDodatneInformacije dodatneInformacije;
    @XmlAttribute(name = "naziv")
    protected String naziv;

    /**
     * Gets the value of the organ property.
     * 
     * @return
     *     possible object is
     *     {@link TOrgan }
     *     
     */
    public TOrgan getOrgan() {
        return organ;
    }

    /**
     * Sets the value of the organ property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOrgan }
     *     
     */
    public void setOrgan(TOrgan value) {
        this.organ = value;
    }

    /**
     * Gets the value of the teloZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link TTeloZahteva }
     *     
     */
    public TTeloZahteva getTeloZahteva() {
        return teloZahteva;
    }

    /**
     * Sets the value of the teloZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTeloZahteva }
     *     
     */
    public void setTeloZahteva(TTeloZahteva value) {
        this.teloZahteva = value;
    }

    /**
     * Gets the value of the dodatneInformacije property.
     * 
     * @return
     *     possible object is
     *     {@link TDodatneInformacije }
     *     
     */
    public TDodatneInformacije getDodatneInformacije() {
        return dodatneInformacije;
    }

    /**
     * Sets the value of the dodatneInformacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDodatneInformacije }
     *     
     */
    public void setDodatneInformacije(TDodatneInformacije value) {
        this.dodatneInformacije = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }

}
