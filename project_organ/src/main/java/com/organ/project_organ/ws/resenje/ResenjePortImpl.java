package com.organ.project_organ.ws.resenje;

import javax.jws.WebService;

import com.organ.project_organ.model.xml_resenje.Resenje;
import com.organ.project_organ.repository.impl.ResenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


@WebService(
        serviceName = "ResenjeService",
        portName = "ResenjePort",
        targetNamespace = "http://soap.spring.com/ws/resenje",
       // wsdlLocation = "classpath:wsdl/Hello.wsdl",
        endpointInterface = "com.organ.project_organ.ws.resenje.ResenjeInterface")
@Component
public class ResenjePortImpl extends SpringBeanAutowiringSupport implements ResenjeInterface{
	@Autowired
	public ResenjeRepository resenjeRepository;

	@Override
	public boolean posaljiResenje(Resenje resenje) {
		try {
			resenjeRepository.save(resenje.getId(), resenje);
		} catch (Exception exception) {
			return false;
		}

		return true;
	}

}
