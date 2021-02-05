package com.xmlproject.project_poverenik.ws.endpoint;

import javax.xml.ws.Endpoint;

import com.xmlproject.project_poverenik.ws.izvestaj.IzvestajPortImpl;
import com.xmlproject.project_poverenik.ws.poruka.PorukaPortImpl;
import com.xmlproject.project_poverenik.ws.zalba.ZalbaPortImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;

	@Autowired
	private IzvestajPortImpl izvestajPortImpl;

	@Autowired
	private ZalbaPortImpl zalbaPortImpl;

	@Autowired
	private PorukaPortImpl porukaPort;


	@Bean
	public Endpoint izvestajEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, izvestajPortImpl);
		endpoint.publish("/report");
		return endpoint;
	}


	@Bean
	public Endpoint zalbaEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, zalbaPortImpl);
		endpoint.publish("/zalba");
		return endpoint;
	}

	@Bean
	public Endpoint porukaEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, porukaPort);
		endpoint.publish("/message");
		return endpoint;
	}
}
