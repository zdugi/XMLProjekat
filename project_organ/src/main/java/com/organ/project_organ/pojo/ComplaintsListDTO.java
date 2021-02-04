package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "complaints")
public class ComplaintsListDTO {
    @XmlElement
    public List<String> complaint;
}

