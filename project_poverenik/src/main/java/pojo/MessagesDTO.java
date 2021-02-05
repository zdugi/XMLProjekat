package pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "wrapper")
public class MessagesDTO {
    @XmlElement
    public List<pojo.MessageDTO> messages;
}
