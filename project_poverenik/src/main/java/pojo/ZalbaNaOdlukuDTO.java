package pojo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "zalilac",
        "organZalilac",
        "organNaKogaSeZali",
        "brojResenja",
        "datumPodnosenja",
        "opisZalbe",
        "dodatneInformacije"
})
@XmlRootElement(name = "zalbaNaOdluku")
public class ZalbaNaOdlukuDTO {

    @XmlElement
    public OsobaDTO zalilac;

    @XmlElement OrganDTO organZalilac;

    @XmlElement
    public OrganDTO organNaKogaSeZali;  // jel se zove organ kao element? pa mzoe,
    // to je svakako samo za front, nema veze s bekom

    @XmlElement
    public BrojResenjaDTO brojResenja; //broj, godina

    @XmlElement
    public DatumPodnosenjaDTO datumPodnosenja;

    @XmlElement(name = "opisZalbe")
    public String opisZalbe;

    @XmlElement
    public DodatneInformacijeDTO dodatneInformacije;



}
