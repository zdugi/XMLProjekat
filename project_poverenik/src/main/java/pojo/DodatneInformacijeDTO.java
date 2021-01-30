package pojo;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dodatneInformacije")
public class DodatneInformacijeDTO {

    @XmlAttribute
    public String mesto;

    @XmlAttribute
    public String datum;

    @XmlElement
    public TrazilacDTO trazilac;



}
