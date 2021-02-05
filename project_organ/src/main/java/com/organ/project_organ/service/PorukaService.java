package com.organ.project_organ.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PorukaService extends AbsService {
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
}
