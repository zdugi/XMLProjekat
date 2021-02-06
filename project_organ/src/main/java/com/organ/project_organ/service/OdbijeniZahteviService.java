package com.organ.project_organ.service;

import com.organ.project_organ.model.odbijeni_zahtevi.OdbijeniZahtevi;
import com.organ.project_organ.model.xml_zahtev.Zahtev;
import com.organ.project_organ.repository.impl.OdbijeniZahteviRepository;
import com.organ.project_organ.repository.impl.ZahtevRepository;
import com.organ.project_organ.security.service.UserService;
import com.organ.project_organ.ws.mail.MailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class OdbijeniZahteviService extends AbsService {
    @Autowired
    private OdbijeniZahteviRepository odbijeniZahteviRepository;

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private UserService userService;

    public static String arrayId = "1";

    public OdbijeniZahteviService() {
        super("", "");
    }

    @PostConstruct
    public void init() {
        try {
            odbijeniZahteviRepository.getOneXML(arrayId);
        } catch (Exception exception) {
            try {
                odbijeniZahteviRepository.save(arrayId, new OdbijeniZahtevi());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public ByteArrayOutputStream getOneRDF(String id) throws Exception {
        return null;
    }

    @Override
    public ByteArrayOutputStream getOneJSON(String id) throws Exception {
        return null;
    }

    public boolean declineRequest(String requestId) {
        OdbijeniZahtevi odbijeni = null;

        try {
            odbijeni = odbijeniZahteviRepository.getOneXML(arrayId);

            if (odbijeni == null)
                odbijeni = new OdbijeniZahtevi();

            List<String> ids = odbijeni.getZahtevID();
            for (String id : ids) {
                if (id.equals(requestId))
                    return false;
            }

            ids.add(requestId);

            odbijeniZahteviRepository.save(arrayId, odbijeni);
            try{
                URL wsdlLocation = new URL("http://localhost:8099/ws/mail?wsdl");
                QName serviceName = new QName("http://soap.spring.com/ws/mail", "MailService");
                QName portName = new QName("http://soap.spring.com/ws/mail", "MailPort");

                javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);

                MailInterface mailI = service.getPort(portName, MailInterface.class);

                String link = "http://localhost:8089/api/requests/pdf/"+ requestId;

                String body = "Vas zahtev je odbijen. Zahtev: "+ requestId;

                Zahtev z = zahtevRepository.getOneXML(requestId);

                String email = userService.findByNameAndSurname(z.getDodatneInformacije().getTrazilac().getOsoba().getIme(), z.getDodatneInformacije().getTrazilac().getOsoba().getPrezime());

                if (mailI.sendMail("Odbijanje zahteva", body, new String[] {email}))
                    System.out.println("Uspesno poslat");
            }
            catch(Exception exception){
                exception.printStackTrace();
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public int getNumberOfDeclined() {
        try {
            OdbijeniZahtevi odbijeniZahtevi = odbijeniZahteviRepository.getOneXML(arrayId);

            if (odbijeniZahtevi == null) return 0;

            return odbijeniZahtevi.getZahtevID().size();

        } catch (Exception exception) {
            return 0;
        }
    }
}
