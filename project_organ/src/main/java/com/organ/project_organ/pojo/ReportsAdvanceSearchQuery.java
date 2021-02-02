package com.organ.project_organ.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class ReportsAdvanceSearchQuery {
    @XmlElement
    public String numberOfSubmittedRegex;

    @XmlElement
    public String numberOfDeclinedRegex;
}
