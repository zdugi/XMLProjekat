package com.organ.project_organ.pojo;

import com.organ.project_organ.model.xml_obavestenja.Obavestenje;
import com.organ.project_organ.model.xml_obavestenja.TDostavljeno;
import com.organ.project_organ.model.xml_obavestenja.TPodnosilac;
import com.organ.project_organ.model.xml_obavestenja.TTeloObavestenja;
import com.organ.project_organ.model.xml_opste.TDatum;
import com.organ.project_organ.model.xml_opste.TOrgan;
import com.organ.project_organ.model.xml_opste.TZakon;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "obavestenje")
public class ObavestenjeDTO {

    @XmlElement
    public OrganDTO organ;

    @XmlElement
    public TeloObavestenjaDTO teloObavestenja;

    @XmlElement
    public List<OpcijaObavestenjeDTO> opcija;

    @XmlElement
    public ZakonDTO zakon;

}
