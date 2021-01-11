package com.xmlproject.project_poverenik.service;

import com.xmlproject.project_poverenik.model.xml_resenje.Resenje;
import com.xmlproject.project_poverenik.repository.ResenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResenjeService {
    @Autowired
    private ResenjeRepository resenjeRepository;

    public void create(Resenje resenje) throws Exception {
        resenjeRepository.save(resenje);
    }

    public Resenje getOne(String id) throws Exception {
        return resenjeRepository.getOne(id);
    }
}
