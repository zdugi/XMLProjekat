package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="otac")
public class Otac {
    @XmlElement(name = "dete")
    public Dete dete;
}
