package pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@XmlRootElement(name = "message")
public class MessageDTO {
    @XmlAttribute
    public String body;

    @XmlAttribute
    public BigInteger time;
}
