package com.xmlproject.project_poverenik.config;

import com.xmlproject.project_poverenik.repository.impl.ZahtevRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformConfiguration {
    @Bean
    public ZahtevRepository zahtevRepository() {
        System.out.println("OVO JE INSTANCIRANO");
        return new ZahtevRepository(
            "/example/zahtev/metadata",
                "/db/sample/zahtev",
                "com.xmlproject.project_poverenik.model.xml_zahtev"
        );
    }
}
