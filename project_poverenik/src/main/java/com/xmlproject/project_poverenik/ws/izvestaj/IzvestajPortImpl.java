
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.xmlproject.project_poverenik.ws.izvestaj;

import com.xmlproject.project_poverenik.model.xml_izvestaj.Izvestaj;
import com.xmlproject.project_poverenik.repository.IzvestajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
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

    @Autowired
	private IzvestajRepository izvestajRepository;

	@Override
	public boolean sendReport(Izvestaj report) {
		String[] parts = report.getAbout().split("/");
		String id = parts[parts.length - 1];
		//String id = UUID.randomUUID().toString();
		try {
			izvestajRepository.save(id, report);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return false;
	}

}
