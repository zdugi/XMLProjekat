//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.05 at 09:53:27 AM CET 
//


package rs.ac.uns.ftn.xml_zalbanaodluku;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import rs.ac.uns.ftn.xml_opste.TAdresa;
import rs.ac.uns.ftn.xml_opste.TDodatneInformacije;


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
 *         &lt;element name="Telo_zalbe_na_odluku" type="{http://ftn.uns.ac.rs/xml_zalbanaodluku}TTelo_Zalbe_Odluka"/>
 *         &lt;element name="DodatneInformacije" type="{http://ftn.uns.ac.rs/xml_opste}TDodatne_informacije"/>
 *         &lt;element name="Napomene">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Napomena" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "teloZalbeNaOdluku",
    "dodatneInformacije",
    "napomene"
})
@XmlRootElement(name = "ZalbaNaOdluku")
public class ZalbaNaOdluku {

    @XmlElement(name = "Primalac", required = true)
    protected String primalac;
    @XmlElement(name = "Adresa_primaoca", required = true)
    protected TAdresa adresaPrimaoca;
    @XmlElement(name = "Telo_zalbe_na_odluku", required = true)
    protected TTeloZalbeOdluka teloZalbeNaOdluku;
    @XmlElement(name = "DodatneInformacije", required = true)
    protected TDodatneInformacije dodatneInformacije;
    @XmlElement(name = "Napomene", required = true)
    protected ZalbaNaOdluku.Napomene napomene;
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
     * Gets the value of the teloZalbeNaOdluku property.
     * 
     * @return
     *     possible object is
     *     {@link TTeloZalbeOdluka }
     *     
     */
    public TTeloZalbeOdluka getTeloZalbeNaOdluku() {
        return teloZalbeNaOdluku;
    }

    /**
     * Sets the value of the teloZalbeNaOdluku property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTeloZalbeOdluka }
     *     
     */
    public void setTeloZalbeNaOdluku(TTeloZalbeOdluka value) {
        this.teloZalbeNaOdluku = value;
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
     * Gets the value of the napomene property.
     * 
     * @return
     *     possible object is
     *     {@link ZalbaNaOdluku.Napomene }
     *     
     */
    public ZalbaNaOdluku.Napomene getNapomene() {
        return napomene;
    }

    /**
     * Sets the value of the napomene property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZalbaNaOdluku.Napomene }
     *     
     */
    public void setNapomene(ZalbaNaOdluku.Napomene value) {
        this.napomene = value;
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
     *         &lt;element name="Napomena" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "napomena"
    })
    public static class Napomene {

        @XmlElement(name = "Napomena")
        protected List<String> napomena;

        /**
         * Gets the value of the napomena property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the napomena property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNapomena().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNapomena() {
            if (napomena == null) {
                napomena = new ArrayList<String>();
            }
            return this.napomena;
        }

    }

}
