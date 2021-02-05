package com.xmlproject.project_poverenik.ws.zalba;


import java.util.ArrayList;

import javax.jws.WebService;

import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ObjectFactory;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.xmlproject.project_poverenik.service.ZalbaNaOdlukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.xmldb.api.base.XMLDBException;


@WebService(
                      serviceName = "ZalbaService",
                      portName = "ZalbaPort",
                      targetNamespace = "http://soap.spring.com/ws/zalba",
                     // wsdlLocation = "classpath:wsdl/Hello.wsdl",
                      endpointInterface = "com.xmlproject.project_poverenik.ws.zalba.ZalbaInterface")
@Component
public class ZalbaPortImpl extends SpringBeanAutowiringSupport implements ZalbaInterface {

	@Autowired
    private ZalbaNaOdlukuService zalbaNaOdlukuService;


	@Override
	public ArrayList<ZalbaNaOdluku> getZalbe() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		ArrayList<ZalbaNaOdluku> zalbe = new ArrayList<ZalbaNaOdluku>();
		try{
			zalbe = zalbaNaOdlukuService.getAllXMLInCollection();
		}catch (Exception exception) {
			exception.printStackTrace();
		}

		// zalbe = pozvati metodu iz servisa
		try {
			//zalbaNaOdlukuService.getList();
			System.out.println("moze");
		} catch (Exception e){
			System.out.println("ne moze");
		}
		return zalbe;
	}

}
