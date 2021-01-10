package com.xmlproject.project_poverenik.service;

import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.repository.ZalbaNaCutanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZalbaNaCutanjeService {

    @Autowired
    private ZalbaNaCutanjeRepository zalbaNaCutanjeRepository;

    public void create (ZalbaNaCutanje zalbaNaCutanje) throws Exception {
        zalbaNaCutanjeRepository.save(zalbaNaCutanje);
    }

    public ZalbaNaCutanje getOne (String id) throws Exception {
        return zalbaNaCutanjeRepository.getOne(id);
    }

}
