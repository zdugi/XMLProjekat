package com.organ.project_organ.config;

import com.organ.project_organ.repository.impl.ObavestenjeRepository;

import com.organ.project_organ.repository.impl.IzvestajRepository;

import com.organ.project_organ.repository.impl.ZahtevRepository;
import com.organ.project_organ.security.repository.UserRepository;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class PlatformConfiguration implements WebMvcConfigurer {
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

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webclient/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Bean
    public IzvestajRepository izvestajRepository() {
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $izvestaj in collection(\"/db/sample/izvestaj/\")\n" +
                "where fn:contains(lower-case($izvestaj), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($izvestaj), \"izvestaj/\")";

        return new IzvestajRepository(
                "/example/izvestaj/metadata",
                "/db/sample/izvestaj",
                "com.organ.project_organ.model.xml_izvestaj",
                textContainQuery
        );
    }

    @Bean
    public ObavestenjeRepository obavestenjeRepository(){
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $obavestenja in collection(\"/db/sample/obavestenja/\")\n" +
                "where fn:contains(lower-case($obavestenja), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($obavestenje), \"obavestenja/\")";

        return new ObavestenjeRepository(
                "/example/obavestenje/metadata",
                "/db/sample/obavestenje",
                "com.organ.project_organ.model.xml_obavestenja",
                textContainQuery
        );
    }

    @Bean
    public UserRepository userRepository() {
        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $korisnik in collection(\"/db/sample/korisnik/\")\n" +
                "where fn:contains(lower-case($korisnik), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($korisnik), \"korisnik/\")";

        return new UserRepository(
                "/example/korisnik/metadata",
                "/db/sample/korisnik",
                "com.organ.project_organ.model.xml_korisnik",
                textContainQuery
        );
    }
}
