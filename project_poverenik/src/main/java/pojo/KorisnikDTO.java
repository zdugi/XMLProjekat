package pojo;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "korisnik")
public class KorisnikDTO {

    @XmlAttribute
    public String ime;

    @XmlAttribute
    public String prezime;

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

    @XmlAttribute
    public String email;

    @XmlAttribute
    public String password;

    @XmlAttribute
    public String role;

    @XmlAttribute
    public String kontakt;
}
