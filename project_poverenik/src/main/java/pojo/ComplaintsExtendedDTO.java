package pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "complaints")
public class ComplaintsExtendedDTO {
    @XmlElement
    public List<Complaint> complaint;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value",
            "status"
    })
    public static class Complaint{
        @XmlElement
        public String value;

        @XmlElement
        public String status;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
