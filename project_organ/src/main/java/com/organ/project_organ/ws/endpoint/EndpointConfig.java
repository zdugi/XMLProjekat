package com.organ.project_organ.ws.endpoint;

import javax.xml.ws.Endpoint;

import com.organ.project_organ.ws.poruka.PorukaPortImpl;
import com.organ.project_organ.ws.resenje.ResenjePortImpl;
import com.organ.project_organ.ws.zahtev.ZahtevPortImpl;
import com.organ.project_organ.ws.zahtev.ZahtevService;
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
	private ZahtevPortImpl zahtevPort;

	@Autowired
	private ResenjePortImpl resenjePort;

	@Autowired
	private PorukaPortImpl porukaPort;

	@Bean
	public Endpoint zahtevEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, zahtevPort);
		endpoint.publish("/request");
		return endpoint;
	}

	@Bean
	public Endpoint resenjeEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, resenjePort);
		endpoint.publish("/resenje");
		return endpoint;
	}

	@Bean
	public Endpoint porukaEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, porukaPort);
		endpoint.publish("/message");
		return endpoint;
	}
}
