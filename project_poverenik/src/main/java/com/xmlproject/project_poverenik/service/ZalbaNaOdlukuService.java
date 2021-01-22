package com.xmlproject.project_poverenik.service;

import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import com.xmlproject.project_poverenik.repository.ZalbaNaCutanjeRepository;
import com.xmlproject.project_poverenik.repository.ZalbaNaOdlukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZalbaNaOdlukuService {

    @Autowired
    private ZalbaNaOdlukuRepository zalbaNaOdlukuRepository;

    public void create (ZalbaNaOdluku zalbaNaOdluku) throws Exception {
        zalbaNaOdlukuRepository.save(zalbaNaOdluku);
    }

    public ZalbaNaOdluku getOne (String id) throws Exception {
        return zalbaNaOdlukuRepository.getOne(id);
    }

}
