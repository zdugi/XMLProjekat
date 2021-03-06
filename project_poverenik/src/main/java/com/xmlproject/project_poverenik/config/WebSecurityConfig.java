package com.xmlproject.project_poverenik.config;


import com.xmlproject.project_poverenik.security.TokenUtils;
import com.xmlproject.project_poverenik.security.auth.RestAuthenticationEntryPoint;
import com.xmlproject.project_poverenik.security.auth.TokenAuthenticationFilter;
import com.xmlproject.project_poverenik.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/ws/**").permitAll()
                .antMatchers("/public/favicon.ico").permitAll()
                .anyRequest().authenticated().and()
                .cors()
                .and().addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userService), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
        web.ignoring()
                .antMatchers(HttpMethod.POST, "/api/auth/**");
        //.antMatchers(HttpMethod.POST, "/cultural-offers/search");
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/public/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js", "/api/complaint/simple-search", "/api/complaint/rdf/**", "/api/complaint/xhtml/**", "/api/complaint/json/**", "/api/complaint/pdf/**",
                "/api/complaint/resolution/rdf/**", "/api/complaint/resolution/xhtml/**", "/api/complaint/resolution/json/**", "/api/complaint/resolution/pdf/**",
                "/api/solution/rdf/**", "/api/solution/xhtml/**", "/api/solution/json/**", "/api/solution/pdf/**", "/api/complaint/resolution/advance-search/**",
                "/api/reports/rdf/**", "/api/reports/xhtml/**", "/api/reports/json/**", "/api/reports/pdf/**"
        );
    }

}