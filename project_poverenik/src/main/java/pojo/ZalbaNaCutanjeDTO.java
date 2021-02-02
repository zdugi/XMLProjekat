package pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "organ",
        "opcija",
        "datumPodnosenja",
        "podaciOZahtevuIInformacijama",
        "dodatneInformacije"
})
@XmlRootElement(name = "zalbaNaCutanje")
public class ZalbaNaCutanjeDTO {

    @XmlElement
    public OrganDTO organ;

    @XmlElement
    public List<OpcijaDTO> opcija;

    @XmlElement
    public DatumPodnosenjaDTO datumPodnosenja;

    @XmlElement(name = "podaciOZahtevuIInformacijama")
    public PodaciOZahtevuIInformacijamaDTO podaciOZahtevuIInformacijama;

    @XmlElement
    public DodatneInformacijeDTO dodatneInformacije;


}
