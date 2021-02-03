
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.organ.project_organ.ws.zahtev;

import com.organ.project_organ.model.xml_zahtev.Zahtev;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(
                      serviceName = "ZahtevService",
                      portName = "ZahtevPort",
                      targetNamespace = "http://soap.spring.com/ws/request",
                     // wsdlLocation = "classpath:wsdl/Hello.wsdl",
                      endpointInterface = "com.organ.project_organ.ws.zahtev.ZahtevInterface")
@Component
public class ZahtevPortImpl extends SpringBeanAutowiringSupport implements ZahtevInterface {
	@Autowired
	public com.organ.project_organ.service.ZahtevService zahtevService;

    private static final Logger LOG = Logger.getLogger(ZahtevPortImpl.class.getName());

	@Override
	public Zahtev getRequest(String id) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		if (id == null)
			return null;

		try {
			return zahtevService.getOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
