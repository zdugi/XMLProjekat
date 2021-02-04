package pojo;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class ResolutionsAdvanceSearchQuery {
    @XmlElement
    public String resenjeZa;

    @XmlElement
    public String doneseno;

    @XmlElement
    public String prihvacena;

    @XmlElement
    public String zalilac;

    @XmlElement
    public String upucujeSe;

    @XmlElement
    public String datumPodnosenjaZahteva;
}
