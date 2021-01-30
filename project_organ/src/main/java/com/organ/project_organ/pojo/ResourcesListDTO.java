package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "resources")
public class ResourcesListDTO {
    @XmlElement
    public List<String> resource;
}
