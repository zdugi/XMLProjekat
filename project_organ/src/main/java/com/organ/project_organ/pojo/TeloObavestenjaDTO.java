package com.organ.project_organ.pojo;

import com.organ.project_organ.model.xml_opste.TAdresa;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TeloObavestenjaDTO {

    @XmlAttribute
    public String godina;

    @XmlAttribute
    public String dan;

    @XmlAttribute
    public String sati;

    @XmlAttribute
    public String pocetniSati;

    @XmlAttribute
    public String zavrsniSati;

    @XmlElement
    public AdresaDTO adresa;

    @XmlAttribute
    public String kancelarija;

    @XmlAttribute
    public String suma;

}
