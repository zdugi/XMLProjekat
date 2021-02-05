package com.organ.project_organ.service;

import com.organ.project_organ.model.odbijeni_zahtevi.OdbijeniZahtevi;
import com.organ.project_organ.repository.impl.OdbijeniZahteviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class OdbijeniZahteviService extends AbsService {
    @Autowired
    private OdbijeniZahteviRepository odbijeniZahteviRepository;

    public static String arrayId = "1";

    public OdbijeniZahteviService() {
        super("", "");
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
