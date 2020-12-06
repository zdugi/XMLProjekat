//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.06 at 07:35:23 PM CET 
//


package rs.ac.uns.ftn.xml_opste;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TZakon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TZakon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Naziv_zakona" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Clan_zakona" type="{http://ftn.uns.ac.rs/xml_opste}TClan_zakona" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZakon", propOrder = {
    "nazivZakona",
    "clanZakona"
})
public class TZakon {

    @XmlElement(name = "Naziv_zakona", required = true)
    protected String nazivZakona;
    @XmlElement(name = "Clan_zakona")
    protected List<TClanZakona> clanZakona;

    /**
     * Gets the value of the nazivZakona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivZakona() {
        return nazivZakona;
    }

    /**
     * Sets the value of the nazivZakona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivZakona(String value) {
        this.nazivZakona = value;
    }

    /**
     * Gets the value of the clanZakona property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clanZakona property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClanZakona().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TClanZakona }
     * 
     * 
     */
    public List<TClanZakona> getClanZakona() {
        if (clanZakona == null) {
            clanZakona = new ArrayList<TClanZakona>();
        }
        return this.clanZakona;
    }

}
