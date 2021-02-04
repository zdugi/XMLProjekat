
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.mailserver.MailServer.ws.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.logging.Logger;

@javax.jws.WebService(
                      serviceName = "MailService",
                      portName = "MailPort",
                      targetNamespace = "http://soap.spring.com/ws/mail",
                      endpointInterface = "com.mailserver.MailServer.ws.mail.MailInterface")
@Component
public class MailPortImpl extends SpringBeanAutowiringSupport implements MailInterface {

	@Autowired
	private JavaMailSender javaMailSender;

    private static final Logger LOG = Logger.getLogger(MailPortImpl.class.getName());

	@Override
	public boolean sendMail(String subject, String body, String[] recipients) {

		try {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(recipients);
			email.setSubject("Studentski projekat XML - " + subject);
			email.setText(body + "\n\nOvaj mejl se salje u sklopu studentskog projekta, izvinjavamo se u slucaju da je dosao greskom u Vase sanduce.");
			javaMailSender.send(email);
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}

		return true;
	}

	

}
