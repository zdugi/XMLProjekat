//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.02 at 05:25:06 PM CET 
//


package rs.ac.uns.ftn.xml_zahtev;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTipovi_zahteva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TTipovi_zahteva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tip_zahteva" type="{http://ftn.uns.ac.rs/xml_zahtev}TTip_zahteva" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTipovi_zahteva", propOrder = {
    "tipZahteva"
})
public class TTipoviZahteva {

    @XmlElement(name = "Tip_zahteva", required = true)
    protected List<TTipZahteva> tipZahteva;

    /**
     * Gets the value of the tipZahteva property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tipZahteva property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipZahteva().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTipZahteva }
     * 
     * 
     */
    public List<TTipZahteva> getTipZahteva() {
        if (tipZahteva == null) {
            tipZahteva = new ArrayList<TTipZahteva>();
        }
        return this.tipZahteva;
    }

}
