package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="opis")
public class OpisDTO {
    @XmlAttribute
    public String tekst;
}
