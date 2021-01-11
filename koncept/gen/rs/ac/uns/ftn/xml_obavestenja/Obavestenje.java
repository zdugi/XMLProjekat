//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.11 at 11:56:04 AM CET 
//


package rs.ac.uns.ftn.xml_obavestenja;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xml_opste.TDatum;
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
 *         &lt;element name="Datum" type="{http://ftn.uns.ac.rs/xml_opste}TDatum"/>
 *         &lt;element name="Organ" type="{http://ftn.uns.ac.rs/xml_opste}TOrgan"/>
 *         &lt;element name="Podaci_podnosioca" type="{http://ftn.uns.ac.rs/xml_obavestenja}TPodnosilac"/>
 *         &lt;element name="Telo_obavestenja" type="{http://ftn.uns.ac.rs/xml_obavestenja}TTelo_obavestenja"/>
 *         &lt;element name="Dostavljeno" type="{http://ftn.uns.ac.rs/xml_obavestenja}TDostavljeno"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Broj_predmeta" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datum",
    "organ",
    "podaciPodnosioca",
    "teloObavestenja",
    "dostavljeno"
})
@XmlRootElement(name = "Obavestenje")
public class Obavestenje {

    @XmlElement(name = "Datum", required = true)
    protected TDatum datum;
    @XmlElement(name = "Organ", required = true)
    protected TOrgan organ;
    @XmlElement(name = "Podaci_podnosioca", required = true)
    protected TPodnosilac podaciPodnosioca;
    @XmlElement(name = "Telo_obavestenja", required = true)
    protected TTeloObavestenja teloObavestenja;
    @XmlElement(name = "Dostavljeno", required = true)
    protected TDostavljeno dostavljeno;
    @XmlAttribute(name = "Broj_predmeta")
    protected String brojPredmeta;
    @XmlAttribute(name = "Naziv")
    protected String naziv;

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link TDatum }
     *     
     */
    public TDatum getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDatum }
     *     
     */
    public void setDatum(TDatum value) {
        this.datum = value;
    }

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
     * Gets the value of the podaciPodnosioca property.
     * 
     * @return
     *     possible object is
     *     {@link TPodnosilac }
     *     
     */
    public TPodnosilac getPodaciPodnosioca() {
        return podaciPodnosioca;
    }

    /**
     * Sets the value of the podaciPodnosioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPodnosilac }
     *     
     */
    public void setPodaciPodnosioca(TPodnosilac value) {
        this.podaciPodnosioca = value;
    }

    /**
     * Gets the value of the teloObavestenja property.
     * 
     * @return
     *     possible object is
     *     {@link TTeloObavestenja }
     *     
     */
    public TTeloObavestenja getTeloObavestenja() {
        return teloObavestenja;
    }

    /**
     * Sets the value of the teloObavestenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTeloObavestenja }
     *     
     */
    public void setTeloObavestenja(TTeloObavestenja value) {
        this.teloObavestenja = value;
    }

    /**
     * Gets the value of the dostavljeno property.
     * 
     * @return
     *     possible object is
     *     {@link TDostavljeno }
     *     
     */
    public TDostavljeno getDostavljeno() {
        return dostavljeno;
    }

    /**
     * Sets the value of the dostavljeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDostavljeno }
     *     
     */
    public void setDostavljeno(TDostavljeno value) {
        this.dostavljeno = value;
    }

    /**
     * Gets the value of the brojPredmeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojPredmeta() {
        return brojPredmeta;
    }

    /**
     * Sets the value of the brojPredmeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojPredmeta(String value) {
        this.brojPredmeta = value;
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
