//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.05 at 02:12:09 PM CET 
//


package rs.ac.uns.ftn.xml_zalba_na_cutanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TRazlozi_zalbe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TRazlozi_zalbe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Razlog_zalbe">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="obelezeno" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRazlozi_zalbe", propOrder = {
    "razlogZalbe"
})
public class TRazloziZalbe {

    @XmlElement(name = "Razlog_zalbe", required = true)
    protected TRazloziZalbe.RazlogZalbe razlogZalbe;

    /**
     * Gets the value of the razlogZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link TRazloziZalbe.RazlogZalbe }
     *     
     */
    public TRazloziZalbe.RazlogZalbe getRazlogZalbe() {
        return razlogZalbe;
    }

    /**
     * Sets the value of the razlogZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRazloziZalbe.RazlogZalbe }
     *     
     */
    public void setRazlogZalbe(TRazloziZalbe.RazlogZalbe value) {
        this.razlogZalbe = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="obelezeno" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class RazlogZalbe {

        @XmlAttribute(name = "obelezeno")
        protected Boolean obelezeno;

        /**
         * Gets the value of the obelezeno property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isObelezeno() {
            return obelezeno;
        }

        /**
         * Sets the value of the obelezeno property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setObelezeno(Boolean value) {
            this.obelezeno = value;
        }

    }

}
