//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.01 at 06:08:18 PM CET 
//


package rs.ac.uns.ftn.xml_resenje;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xml_opste.TDatum;
import rs.ac.uns.ftn.xml_opste.TOsoba;


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
 *         &lt;element name="Sekcija" type="{http://ftn.uns.ac.rs/xml_resenje}TSekcija" maxOccurs="unbounded"/>
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
    "sekcija",
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
    @XmlElement(name = "Sekcija", required = true)
    protected List<TSekcija> sekcija;
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
     * Gets the value of the sekcija property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sekcija property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSekcija().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSekcija }
     * 
     * 
     */
    public List<TSekcija> getSekcija() {
        if (sekcija == null) {
            sekcija = new ArrayList<TSekcija>();
        }
        return this.sekcija;
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

}
