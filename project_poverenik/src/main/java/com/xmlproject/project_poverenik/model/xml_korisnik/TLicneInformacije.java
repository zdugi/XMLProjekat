//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.02 at 05:25:10 PM CET 
//


package com.xmlproject.project_poverenik.model.xml_korisnik;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.xmlproject.project_poverenik.model.xml_opste.TAdresa;
import com.xmlproject.project_poverenik.model.xml_opste.TOsoba;


/**
 * <p>Java class for TLicne_Informacije complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TLicne_Informacije">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Osoba" type="{http://ftn.uns.ac.rs/xml_opste}TOsoba"/>
 *         &lt;element name="Adresa" type="{http://ftn.uns.ac.rs/xml_opste}TAdresa"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLicne_Informacije", propOrder = {
    "osoba",
    "adresa"
})
public class TLicneInformacije {

    @XmlElement(name = "Osoba", required = true)
    protected TOsoba osoba;
    @XmlElement(name = "Adresa", required = true)
    protected TAdresa adresa;

    /**
     * Gets the value of the osoba property.
     * 
     * @return
     *     possible object is
     *     {@link TOsoba }
     *     
     */
    public TOsoba getOsoba() {
        return osoba;
    }

    /**
     * Sets the value of the osoba property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOsoba }
     *     
     */
    public void setOsoba(TOsoba value) {
        this.osoba = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresa }
     *     
     */
    public TAdresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresa }
     *     
     */
    public void setAdresa(TAdresa value) {
        this.adresa = value;
    }

}
