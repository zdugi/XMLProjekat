package com.organ.project_organ.config;

import com.organ.project_organ.repository.impl.ZahtevRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformConfiguration {
    @Bean
    public ZahtevRepository zahtevRepository() {
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $zahtev in collection(\"/db/sample/zahtev/\")\n" +
                "where fn:contains(lower-case($zahtev), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($zahtev), \"zahtev/\")";

        return new ZahtevRepository(
            "/example/zahtev/metadata",
                "/db/sample/zahtev",
                "com.organ.project_organ.model.xml_zahtev",
                textContainQuery
        );
    }
}
