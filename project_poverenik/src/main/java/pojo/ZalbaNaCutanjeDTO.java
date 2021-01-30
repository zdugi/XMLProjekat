package pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "organ",
        "opcije",
        "datumPodnosenja",
        "podaciOZahtevuIInformacijama",
        "dodatneInformacije"
})
@XmlRootElement(name = "zalbaNaCutanje")
public class ZalbaNaCutanjeDTO {

    @XmlElement
    public OrganDTO organ;

    @XmlElement
    public List<OpcijaDTO> opcije;

    @XmlElement
    public DatumPodnosenjaDTO datumPodnosenja;

    @XmlElement(name = "podaciOZahtevuIInformacijama")
    public String podaciOZahtevuIInformacijama;

    @XmlElement
    public DodatneInformacijeDTO dodatneInformacije;


}
