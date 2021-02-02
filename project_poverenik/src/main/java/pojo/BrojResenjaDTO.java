package pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "brojResenja")
public class BrojResenjaDTO {

    @XmlAttribute
    public String broj;

    @XmlAttribute
    public String godina;
}
