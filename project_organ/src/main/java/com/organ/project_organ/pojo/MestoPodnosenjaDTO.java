package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "mestoPodnosenja")
public class MestoPodnosenjaDTO {
    @XmlAttribute
    public String naziv;
}
