package pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "idZahteva",
        "opcija",
        "podaciOZahtevuIInformacijama"

})
@XmlRootElement(name = "zalbaNaCutanje")
public class ZalbaNaCutanjeDTO {


    @XmlElement
    public List<OpcijaDTO> opcija;


    @XmlElement(name = "podaciOZahtevuIInformacijama")
    public PodaciOZahtevuIInformacijamaDTO podaciOZahtevuIInformacijama;


    @XmlElement
    public String idZahteva;


}
