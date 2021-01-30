package pojo;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "datumPodnosenja")
public class DatumPodnosenjaDTO {

    @XmlAttribute
    public String datumPodnosenjaA;

}
