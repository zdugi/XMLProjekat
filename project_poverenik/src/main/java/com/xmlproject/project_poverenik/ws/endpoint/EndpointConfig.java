package com.xmlproject.project_poverenik.ws.endpoint;

import javax.xml.ws.Endpoint;

import com.xmlproject.project_poverenik.ws.izvestaj.IzvestajPortImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint izvestajEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new IzvestajPortImpl());
		endpoint.publish("/report");
		return endpoint;
	}
	
}
