package com.mailserver.MailServer.ws.endpoint;

import javax.xml.ws.Endpoint;

import com.mailserver.MailServer.ws.mail.MailPortImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
public class EndpointConfig {


	@Autowired
	private Bus bus;

	@Autowired
	private MailPortImpl mailPortImpl;


	@Bean
	public Endpoint mailEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, mailPortImpl);
		endpoint.publish("/mail");
		return endpoint;
	}
}
