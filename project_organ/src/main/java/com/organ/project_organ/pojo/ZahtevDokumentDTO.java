package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "organ",
        "zahtevam"
})
@XmlRootElement(name = "zahtev")
public class ZahtevDokumentDTO {
    @XmlElement
    public OrganDTO organ;

    @XmlElement
    public ZahtevDTO zahtevam;

    //@XmlElement
    //public MestoPodnosenjaDTO mestoPodnosenja;
}

