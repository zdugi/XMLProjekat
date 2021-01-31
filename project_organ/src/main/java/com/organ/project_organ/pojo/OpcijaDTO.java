package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="opcija")
public class OpcijaDTO {
    @XmlAttribute
    public Boolean cekiran;

    @XmlAttribute
    public String tekst;

    @XmlElement
    public List<DostavaDTO> dostava;
}
