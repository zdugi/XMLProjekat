package pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "organ")
public class OrganDTO {
    @XmlAttribute
    public String naziv;

    @XmlElement
    public AdresaDTO adresa;
}
