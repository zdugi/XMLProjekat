package com.organ.project_organ.ws.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2020-12-26T14:56:21.140+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://soap.spring.com/ws/hello", name = "Hello")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Hello {

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://soap.spring.com/ws/hello", partName = "return")
    public String sayHi(
        @WebParam(partName = "text", name = "text")
        String text
    );
}