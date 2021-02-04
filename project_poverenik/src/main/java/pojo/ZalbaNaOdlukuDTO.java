package pojo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "idZahteva",
        "zalilac",
        "organZalilac",
        "brojResenja",  // sta je broj resenja isuse hriste
        "opisZalbe"
})
@XmlRootElement(name = "zalbaNaOdluku")
public class ZalbaNaOdlukuDTO {

    @XmlElement
    public OsobaDTO zalilac;

    @XmlElement
    public OrganDTO organZalilac;

    @XmlElement
    public BrojResenjaDTO brojResenja; //broj, godina

    @XmlElement
    public String idZahteva;

    @XmlElement(name = "opisZalbe")
    public String opisZalbe;

}
