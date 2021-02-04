
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.xmlproject.project_poverenik.ws.izvestaj;

import com.xmlproject.project_poverenik.model.xml_izvestaj.Izvestaj;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;



@WebService(
                      serviceName = "IzvestajService",
                      portName = "IzvestajPort",
                      targetNamespace = "http://soap.spring.com/ws/report",
                     // wsdlLocation = "classpath:wsdl/Hello.wsdl",
                      endpointInterface = "com.xmlproject.project_poverenik.ws.izvestaj.IzvestajInterface")
@Component
public class IzvestajPortImpl implements IzvestajInterface {

    private static final Logger LOG = Logger.getLogger(IzvestajPortImpl.class.getName());

	@Override
	public boolean sendReport(Izvestaj report) {
		LOG.info(">>>>>>>>>>>>>> Primljen je izvestaj od organa vlasti");
		LOG.info("Dopuniti implementaciju u com/xmlproject/project_poverenik/ws/izvestaj/IzvestajPortImpl.java");
		LOG.info("Dobijena vrednost: " + report.getPodnetiZahtevi().getValue());
		return false;
	}

}
