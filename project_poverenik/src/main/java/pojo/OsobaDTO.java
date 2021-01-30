package pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "osoba")
public class OsobaDTO {
    @XmlAttribute
    public String ime;

    @XmlAttribute
    public String prezime;
}
