package com.xmlproject.project_poverenik.config;

import com.xmlproject.project_poverenik.repository.PorukaRepository;
import com.xmlproject.project_poverenik.repository.ResenjeRepository;
import com.xmlproject.project_poverenik.repository.ZalbaNaCutanjeRepository;
import com.xmlproject.project_poverenik.repository.ZalbaNaOdlukuRepository;
import com.xmlproject.project_poverenik.security.repository.UserRepository;
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

    @Bean
    public ZalbaNaOdlukuRepository zalbaNaOdlukuRepository() {
        System.out.println("OVO JE INSTANCIRANO");
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $zalbanaodluku in collection(\"/db/sample/zalbanaodluku/\")\n" +
                "where fn:contains(lower-case($zalbanaodluku), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($zalbanaodluku), \"zalbanaodluku/\")";

        return new ZalbaNaOdlukuRepository(
                "/example/zalba_na_odluku/metadata",
                "/db/sample/zalbanaodluku",
                "com.xmlproject.project_poverenik.model.xml_zalbanaodluku",
                textContainQuery
        );
    }

    @Bean
    public UserRepository userRepository() {
        System.out.println("OVO JE INSTANCIRANO");
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $korisnik in collection(\"/db/sample/korisnik/\")\n" +
                "where fn:contains(lower-case($korisnik), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($korisnik), \"korisnik/\")";

        return new UserRepository(
                "/example/korisnik/metadata",
                "/db/sample/korisnik",
                "com.xmlproject.project_poverenik.model.xml_korisnik",
                textContainQuery
        );
    }

    @Bean
    public ResenjeRepository resenjeRepository() {
        System.out.println("OVO JE INSTANCIRANO");
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $resenje in collection(\"/db/sample/resenje/\")\n" +
                "where fn:contains(lower-case($resenje), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($resenje), \"resenje/\")";

        return new ResenjeRepository(
                "/example/resenje/metadata",
                "/db/sample/resenje",
                "com.xmlproject.project_poverenik.model.xml_resenje",
                textContainQuery
        );
    }

    @Bean
    public PorukaRepository porukaRepository() {
        String textContainQuery = "";

        return new PorukaRepository(
                "/example/poruka/metadata",
                "/db/sample/poruka",
                "com.xmlproject.project_poverenik.model.poruka",
                textContainQuery
        );
    }
}
