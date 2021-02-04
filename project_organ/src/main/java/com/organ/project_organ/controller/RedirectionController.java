package com.organ.project_organ.controller;

import com.organ.project_organ.ws.mail.MailInterface;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@Controller
@RequestMapping(path = "/")
public class RedirectionController {

    @GetMapping
    public void redirectToStatic(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/webclient/index.html");
        httpServletResponse.setStatus(302);
    }

    @GetMapping(path = "/hello", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String helloWorld() throws MalformedURLException {
        URL wsdlLocation = new URL("http://localhost:8099/ws/mail?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/mail", "MailService");
        QName portName = new QName("http://soap.spring.com/ws/mail", "MailPort");

        Service service = Service.create(wsdlLocation, serviceName);

        MailInterface mailI = service.getPort(portName, MailInterface.class);

        if (mailI.sendMail("Naslov mejla", "Ovde ide sadrzaj mejla", new String[] {"zdravko.dugi@gmail.com", "zdugi@yandex.com"}))
            System.out.println("Uspesno poslat");



        return "<html><body><div style=\"margin: 80px auto; width: 457px;\"><img width=\"457\" src=\"https://i.imgur.com/0l51lap.jpeg\"/></div></body></html>";
    }
}
