package com.xmlproject.project_poverenik.config;

import com.xmlproject.project_poverenik.repository.ZalbaNaCutanjeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformConfiguration {
    @Bean
    public ZalbaNaCutanjeRepository zalbaNaCutanjeRepository() {
        System.out.println("OVO JE INSTANCIRANO");
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $zalbanacutanje in collection(\"/db/sample/zalbanacutanje/\")\n" +
                "where fn:contains(lower-case($zalbanacutanje), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($zalbanacutanje), \"zalbanacutanje/\")";

        return new ZalbaNaCutanjeRepository(
                "/example/zalba_na_cutanje/metadata",
                "/db/sample/zalbanacutanje",
                "com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje",
                textContainQuery
        );
    }
}
