package pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "podaciOZahtevuIInformacijama")
public class PodaciOZahtevuIInformacijamaDTO {

    @XmlAttribute
    public String podaci;

}
