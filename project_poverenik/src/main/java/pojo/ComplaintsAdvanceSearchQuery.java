package pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class ComplaintsAdvanceSearchQuery {
    @XmlElement
    public String submissionDateRegex;

    @XmlElement
    public String authorityRegex;

    @XmlElement
    public String placeRegex;

    @XmlElement
    public String stateRegex;

    @XmlElement
    public String applicantRegex;

}
