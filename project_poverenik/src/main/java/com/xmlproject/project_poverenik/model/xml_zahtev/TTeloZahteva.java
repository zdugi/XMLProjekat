//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.22 at 12:08:30 PM CET 
//


package com.xmlproject.project_poverenik.model.xml_zahtev;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTelo_zahteva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TTelo_zahteva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Paragraf" type="{http://ftn.uns.ac.rs/xml_zahtev}TParagraf"/>
 *         &lt;element name="Informacije" type="{http://ftn.uns.ac.rs/xml_zahtev}TInformacije"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTelo_zahteva", propOrder = {
    "paragraf",
    "informacije"
})
public class TTeloZahteva {

    @XmlElement(name = "Paragraf", required = true)
    protected TParagraf paragraf;
    @XmlElement(name = "Informacije", required = true)
    protected TInformacije informacije;

    /**
     * Gets the value of the paragraf property.
     * 
     * @return
     *     possible object is
     *     {@link TParagraf }
     *     
     */
    public TParagraf getParagraf() {
        return paragraf;
    }

    /**
     * Sets the value of the paragraf property.
     * 
     * @param value
     *     allowed object is
     *     {@link TParagraf }
     *     
     */
    public void setParagraf(TParagraf value) {
        this.paragraf = value;
    }

    /**
     * Gets the value of the informacije property.
     * 
     * @return
     *     possible object is
     *     {@link TInformacije }
     *     
     */
    public TInformacije getInformacije() {
        return informacije;
    }

    /**
     * Sets the value of the informacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link TInformacije }
     *     
     */
    public void setInformacije(TInformacije value) {
        this.informacije = value;
    }

}
