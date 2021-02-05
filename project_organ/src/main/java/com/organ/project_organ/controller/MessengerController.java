package com.organ.project_organ.controller;

import com.organ.project_organ.model.poruka.Poruka;
import com.organ.project_organ.ws.poruka.PorukaInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping(path = "api/message")
public class MessengerController {
    @GetMapping
    public ResponseEntity<?> sendMessage(@RequestParam @Valid String message) throws MalformedURLException {
        URL wsdlLocation = new URL("http://localhost:8081/ws/message?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/message", "PorukaService");
        QName portName = new QName("http://soap.spring.com/ws/message", "PorukaPort");

        Service service = Service.create(wsdlLocation, serviceName);

        PorukaInterface porukaI = service.getPort(portName, PorukaInterface.class);
        Poruka msg = new Poruka();
        msg.setTelo("Poverenik: " + message);
        msg.setVreme(BigInteger.valueOf(System.currentTimeMillis() / 1000L));

        if(!porukaI.sendMessage(msg))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
