package pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dostava")
public class DostavaDTO {
    @XmlAttribute
    public Boolean cekiran;

    @XmlAttribute
    public String tekst;

    @XmlAttribute
    public String dodatno;
}
