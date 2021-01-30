package pojo;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "trazilac")
public class TrazilacDTO {

    @XmlElement
    public OsobaDTO osoba;

    @XmlElement
    public AdresaDTO adresa;

    @XmlAttribute
    public String kontakt;

}
