//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.11 at 01:59:19 PM CET 
//


package com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.xmlproject.project_poverenik.model.xml_opste.*;

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
 *         &lt;element name="Primalac" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Adresa_primaoca" type="{http://ftn.uns.ac.rs/xml_opste}TAdresa"/>
 *         &lt;element name="Telo_zalbe" type="{http://ftn.uns.ac.rs/xml_zalba_na_cutanje}TTelo_Zalbe"/>
 *         &lt;element name="Dodatne_informacije" type="{http://ftn.uns.ac.rs/xml_opste}TDodatne_informacije"/>
 *       &lt;/sequence>
 *       &lt;attribute name="naziv" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "primalac",
    "adresaPrimaoca",
    "teloZalbe",
    "dodatneInformacije"
})
@XmlRootElement(name = "ZalbaNaCutanje")
public class ZalbaNaCutanje {

    @XmlElement(name = "Primalac", required = true)
    protected String primalac;
    @XmlElement(name = "Adresa_primaoca", required = true)
    protected TAdresa adresaPrimaoca;
    @XmlElement(name = "Telo_zalbe", required = true)
    protected TTeloZalbe teloZalbe;
    @XmlElement(name = "Dodatne_informacije", required = true)
    protected TDodatneInformacije dodatneInformacije;
    @XmlAttribute(name = "naziv")
    protected String naziv;
    @XmlAttribute(name = "rel")
    protected String rel;
    @XmlAttribute(name = "href")
    protected String href;
    @XmlAttribute(name = "about")
    protected String about;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimalac(String value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the adresaPrimaoca property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresa }
     *     
     */
    public TAdresa getAdresaPrimaoca() {
        return adresaPrimaoca;
    }

    /**
     * Sets the value of the adresaPrimaoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresa }
     *     
     */
    public void setAdresaPrimaoca(TAdresa value) {
        this.adresaPrimaoca = value;
    }

    /**
     * Gets the value of the teloZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link TTeloZalbe }
     *     
     */
    public TTeloZalbe getTeloZalbe() {
        return teloZalbe;
    }

    /**
     * Sets the value of the teloZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTeloZalbe }
     *     
     */
    public void setTeloZalbe(TTeloZalbe value) {
        this.teloZalbe = value;
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

    /**
     * Gets the value of the rel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRel() {
        return rel;
    }

    /**
     * Sets the value of the rel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRel(String value) {
        this.rel = value;
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
