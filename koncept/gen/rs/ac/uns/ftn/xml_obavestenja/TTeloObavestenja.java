//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.02 at 05:25:04 PM CET 
//


package rs.ac.uns.ftn.xml_obavestenja;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TZakon;


/**
 * <p>Java class for TTelo_obavestenja complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TTelo_obavestenja">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Zakon" type="{http://ftn.uns.ac.rs/xml_opste}TZakon"/>
 *         &lt;element name="Godina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Opis">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="Savet" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Dan">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="31"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Sati" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Pocetni_sat">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="23"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Zavrsni_sat">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="23"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Adresa" type="{http://ftn.uns.ac.rs/xml_opste}TAdresa"/>
 *         &lt;element name="Kancelarija" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Suma" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTelo_obavestenja", propOrder = {
    "content"
})
public class TTeloObavestenja {

    @XmlElementRefs({
        @XmlElementRef(name = "Zakon", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Sati", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Zavrsni_sat", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Dan", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Pocetni_sat", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Opis", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Adresa", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Kancelarija", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Suma", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class),
        @XmlElementRef(name = "Godina", namespace = "http://ftn.uns.ac.rs/xml_obavestenja", type = JAXBElement.class)
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
     * {@link JAXBElement }{@code <}{@link TZakon }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link TTeloObavestenja.Opis }{@code >}
     * {@link JAXBElement }{@code <}{@link TAdresa }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
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
     *       &lt;attribute name="Savet" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    public static class Opis {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "Savet")
        @XmlSchemaType(name = "anySimpleType")
        protected String savet;

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
         * Gets the value of the savet property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSavet() {
            return savet;
        }

        /**
         * Sets the value of the savet property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSavet(String value) {
            this.savet = value;
        }

    }

}
