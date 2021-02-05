package com.xmlproject.project_poverenik.controller;


import com.xmlproject.project_poverenik.model.poruka.Poruka;
import com.xmlproject.project_poverenik.service.ZalbaNaCutanjeService;
import com.xmlproject.project_poverenik.service.ZalbaNaOdlukuService;
import com.xmlproject.project_poverenik.ws.poruka.PorukaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pojo.MessageDTO;
import pojo.MessagesDTO;

import javax.validation.Valid;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/message")
public class MessengerController {
    @Autowired
    private com.xmlproject.project_poverenik.service.PorukaService porukaService;

    @Autowired
    private ZalbaNaOdlukuService zalbaNaOdlukuService;

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;


    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO message) throws MalformedURLException {
        if (message.body == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        URL wsdlLocation = new URL("http://localhost:8089/ws/message?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/message", "PorukaService");
        QName portName = new QName("http://soap.spring.com/ws/message", "PorukaPort");

        Service service = Service.create(wsdlLocation, serviceName);

        PorukaInterface porukaI = service.getPort(portName, PorukaInterface.class);
        Poruka msg = new Poruka();
        String primljeno = message.body;
        String[] telo = primljeno.split(" ");
        String tipZalbe = telo[0];
        String id = telo[1];
        String link = "";
        System.out.println(tipZalbe + "\n\n\n\n*****");
        if (tipZalbe.equals("cutanje")) {
            // promijeniti status zalbe
            zalbaNaCutanjeService.setPrihvaceno(id.substring(0, id.length() - 4), "чека се одговор органа власти");
            link = "http://localhost:8081/api/complaint/pdf/" + id;
        }
        else {
            zalbaNaOdlukuService.setPrihvaceno(id.substring(0, id.length() - 4), "чека се одговор органа власти");
            link = "http://localhost:8081/api/complaint/resolution/pdf/" + id;
        }
        String mess = "Poverenik: Podneta je zalba " + link + ". Molimo vas da se u roku od 5 dana izjasnite da li"+
                " zalbu odbijate ili prihvatate.";
        msg.setTelo("Poverenik: " + mess);
        msg.setVreme(BigInteger.valueOf(System.currentTimeMillis() / 1000L));

        if(!porukaI.sendMessage(msg))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println(message.body);

        porukaService.saveMessage(msg);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.time = msg.getVreme();
        messageDTO.body = msg.getTelo();

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    /*@PreAuthorize("hasRole('ROLE_POVERENIK')")
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendMessage(@RequestBody String id) throws MalformedURLException {
        if (message.body == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        URL wsdlLocation = new URL("http://localhost:8081/ws/message?wsdl");
        QName serviceName = new QName("http://soap.spring.com/ws/message", "PorukaService");
        QName portName = new QName("http://soap.spring.com/ws/message", "PorukaPort");

        Service service = Service.create(wsdlLocation, serviceName);

        PorukaInterface porukaI = service.getPort(portName, PorukaInterface.class);
        Poruka msg = new Poruka();
        msg.setTelo("Sluzbenik: " + message.body);
        msg.setVreme(BigInteger.valueOf(System.currentTimeMillis() / 1000L));

        if(!porukaI.sendMessage(msg))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        porukaService.saveMessage(msg);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.time = msg.getVreme();
        messageDTO.body = msg.getTelo();

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }*/

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getMessages() {
        List<MessageDTO> messageDTOList = new ArrayList<>();

        for (Poruka p : porukaService.getAll()) {
            MessageDTO msg = new MessageDTO();
            msg.body = p.getTelo();
            msg.time = p.getVreme();

            messageDTOList.add(msg);
        }

        MessagesDTO messagesDTO = new MessagesDTO();
        messagesDTO.messages = messageDTOList;

        return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
    }
}
