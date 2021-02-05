package com.xmlproject.project_poverenik.service;

import com.xmlproject.project_poverenik.model.poruka.Poruka;
import com.xmlproject.project_poverenik.repository.PorukaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PorukaService extends AbsService {
    @Autowired
    private PorukaRepository porukaRepository;

    public PorukaService() {
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

    public boolean saveMessage(Poruka poruka) {
        try {
            porukaRepository.save(UUID.randomUUID().toString(), poruka);
        } catch (Exception exception) {
            return false;
        }

        return true;
    }

    public List<Poruka> getAll() {
        ArrayList<Poruka> elements = new ArrayList<>();
        try {
            for (String res : porukaRepository.listResources()) {
                elements.add(porukaRepository.getOneXML(res));
            }
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return elements;
    }
}
