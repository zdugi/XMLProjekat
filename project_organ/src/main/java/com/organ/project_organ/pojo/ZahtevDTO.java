package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "zahtev")
public class ZahtevDTO {
    @XmlElement
    public List<OpcijaDTO> opcija;

    @XmlElement
    public OpisDTO opis;
}
