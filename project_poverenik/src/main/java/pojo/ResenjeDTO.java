package pojo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id_zalbe",
        "resenje_ukratko",
        "obrazlozenje",
        "prihvacena"
})
@XmlRootElement(name = "resenje")
public class ResenjeDTO {

    @XmlElement
    public String id_zalbe;

    @XmlElement
    public String resenje_ukratko;

    @XmlElement
    public String obrazlozenje;

    @XmlElement
    public String prihvacena;      // dodati u zalbu, sacuvati prilikom kreiranja rjesenja



}
