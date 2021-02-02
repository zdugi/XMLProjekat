//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.02 at 05:25:05 PM CET 
//


package com.xmlproject.project_poverenik.model.xml_resenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import com.xmlproject.project_poverenik.model.xml_opste.TDatum;
import com.xmlproject.project_poverenik.model.xml_opste.TOsoba;



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
 *         &lt;element name="Zalba" type="{http://ftn.uns.ac.rs/xml_resenje}TZalba"/>
 *         &lt;element name="Uvod" type="{http://ftn.uns.ac.rs/xml_resenje}TUvod"/>
 *         &lt;element name="Resenje_Ukratko">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="prihvaceno" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Obrazlozenje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Poverenik" type="{http://ftn.uns.ac.rs/xml_opste}TOsoba"/>
 *       &lt;/sequence>
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
    "datum",
    "zalba",
    "uvod",
    "resenjeUkratko",
    "obrazlozenje",
    "poverenik"
})
@XmlRootElement(name = "Resenje")
public class Resenje {

    @XmlElement(name = "Datum", required = true)
    protected TDatum datum;
    @XmlElement(name = "Zalba", required = true)
    protected TZalba zalba;
    @XmlElement(name = "Uvod", required = true)
    protected TUvod uvod;
    @XmlElement(name = "Resenje_Ukratko", required = true)
    protected Resenje.ResenjeUkratko resenjeUkratko;
    @XmlElement(name = "Obrazlozenje", required = true)
    protected String obrazlozenje;
    @XmlElement(name = "Poverenik", required = true)
    protected TOsoba poverenik;
    @XmlAttribute(name = "rel")
    protected String rel;
    @XmlAttribute(name = "href")
    protected String href;
    @XmlAttribute(name = "about")
    protected String about;
    @XmlAttribute(name = "id")
    protected String id;

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
     * Gets the value of the zalba property.
     * 
     * @return
     *     possible object is
     *     {@link TZalba }
     *     
     */
    public TZalba getZalba() {
        return zalba;
    }

    /**
     * Sets the value of the zalba property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZalba }
     *     
     */
    public void setZalba(TZalba value) {
        this.zalba = value;
    }

    /**
     * Gets the value of the uvod property.
     * 
     * @return
     *     possible object is
     *     {@link TUvod }
     *     
     */
    public TUvod getUvod() {
        return uvod;
    }

    /**
     * Sets the value of the uvod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TUvod }
     *     
     */
    public void setUvod(TUvod value) {
        this.uvod = value;
    }

    /**
     * Gets the value of the resenjeUkratko property.
     * 
     * @return
     *     possible object is
     *     {@link Resenje.ResenjeUkratko }
     *     
     */
    public Resenje.ResenjeUkratko getResenjeUkratko() {
        return resenjeUkratko;
    }

    /**
     * Sets the value of the resenjeUkratko property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resenje.ResenjeUkratko }
     *     
     */
    public void setResenjeUkratko(Resenje.ResenjeUkratko value) {
        this.resenjeUkratko = value;
    }

    /**
     * Gets the value of the obrazlozenje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObrazlozenje() {
        return obrazlozenje;
    }

    /**
     * Sets the value of the obrazlozenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObrazlozenje(String value) {
        this.obrazlozenje = value;
    }

    /**
     * Gets the value of the poverenik property.
     * 
     * @return
     *     possible object is
     *     {@link TOsoba }
     *     
     */
    public TOsoba getPoverenik() {
        return poverenik;
    }

    /**
     * Sets the value of the poverenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOsoba }
     *     
     */
    public void setPoverenik(TOsoba value) {
        this.poverenik = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="prihvaceno" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ResenjeUkratko {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "prihvaceno")
        protected Boolean prihvaceno;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the prihvaceno property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isPrihvaceno() {
            return prihvaceno;
        }

        /**
         * Sets the value of the prihvaceno property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setPrihvaceno(Boolean value) {
            this.prihvaceno = value;
        }

    }

}
