package com.organ.project_organ.ws.endpoint;

import javax.xml.ws.Endpoint;

import com.organ.project_organ.ws.hello.HelloPortImpl;
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
	public Endpoint helloMessageEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloPortImpl());
		endpoint.publish("/helloMessage");
		return endpoint;
	}
	
	
}
