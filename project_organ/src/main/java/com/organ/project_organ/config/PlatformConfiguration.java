package com.organ.project_organ.config;

import com.organ.project_organ.repository.impl.ObavestenjeRepository;
import com.organ.project_organ.repository.impl.ZahtevRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformConfiguration {
    @Bean
    public ZahtevRepository zahtevRepository() {
        return new ZahtevRepository(
            "/example/zahtev/metadata",
                "/db/sample/zahtev",
                "com.organ.project_organ.model.xml_zahtev"
        );
    }

    @Bean
    public ObavestenjeRepository obavestenjeRepository(){
        return new ObavestenjeRepository(
                "/example/obavestenje/metadata",
                "/db/sample/obavestenje",
                "com.organ.project_organ.model.xml_obavestenja"
        );
    }
}
