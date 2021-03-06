package com.organ.project_organ.config;



import com.organ.project_organ.security.TokenUtils;
import com.organ.project_organ.security.auth.RestAuthenticationEntryPoint;
import com.organ.project_organ.security.auth.TokenAuthenticationFilter;
import com.organ.project_organ.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
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
                .antMatchers("/hello").permitAll()
                .antMatchers("/webclient/**").permitAll()
                .antMatchers("/api/requests/pdf/**").permitAll()
                .antMatchers("/api/requests/xhtml/**").permitAll()
                .antMatchers("/api/requests/rdf/**").permitAll()
                .antMatchers("/api/requests/json/**").permitAll()
                .antMatchers("/api/reports/pdf/**").permitAll()
                .antMatchers("/api/reports/xhtml/**").permitAll()
                .antMatchers("/api/reports/rdf/**").permitAll()
                .antMatchers("/api/reports/json/**").permitAll()
                .antMatchers("/api/notification/pdf/**").permitAll()
                .antMatchers("/api/notification/xhtml/**").permitAll()
                .antMatchers("/api/notification/rdf/**").permitAll()
                .antMatchers("/api/notification/json/**").permitAll()
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
                .antMatchers(HttpMethod.POST, "/api/auth/login", "/api/complaint/**");
        //.antMatchers(HttpMethod.POST, "/cultural-offers/search");
        web.ignoring().antMatchers(HttpMethod.GET, "/webclient/**", "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js", "/api/complaint/**"
        );
    }

}