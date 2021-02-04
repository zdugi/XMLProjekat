package com.organ.project_organ.ws.resenje;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.organ.project_organ.model.xml_resenje.Resenje;

@WebService(targetNamespace = "http://soap.spring.com/ws/resenje", name = "ResenjeInterface")
@XmlSeeAlso({com.organ.project_organ.model.xml_resenje.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ResenjeInterface {
	
	@WebMethod
    @WebResult(name = "return", targetNamespace = "http://soap.spring.com/ws/resenje", partName = "return")
    public boolean posaljiResenje(@WebParam(partName = "resenje", name = "resenje") Resenje resenje);
    

}
