package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "adresa")
public class AdresaDTO {
    @XmlAttribute
    public String ulica;

    @XmlAttribute
    public String broj;

    @XmlAttribute
    public String postanskiBroj;

    @XmlAttribute
    public String mesto;

    @XmlAttribute
    public String drzava;
}
