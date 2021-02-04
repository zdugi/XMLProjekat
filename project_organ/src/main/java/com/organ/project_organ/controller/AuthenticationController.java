package com.organ.project_organ.controller;



import com.organ.project_organ.model.xml_korisnik.Korisnik;
import com.organ.project_organ.pojo.KorisnikDTO;
import com.organ.project_organ.pojo.KorisnikLoginDTO;
import com.organ.project_organ.pojo.KorisnikTokenStateDTO;
import com.organ.project_organ.security.TokenUtils;
import com.organ.project_organ.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logOut(HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> logIn(@RequestBody KorisnikLoginDTO authenticationRequest) {

        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.email,
                            authenticationRequest.password));
            // Ubaci korisnika u trenutni security kontekst
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Kreiraj token za tog korisnika
            Korisnik user = (Korisnik) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user); // prijavljujemo se na sistem sa email adresom
            int expiresIn = tokenUtils.getExpiredIn();

            // Vrati token kao odgovor na uspesnu autentifikaciju
            return ResponseEntity.ok(new KorisnikTokenStateDTO(jwt, expiresIn));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("");
        }



    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> register(@RequestBody KorisnikDTO userDTO){
    	try {
            userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
    	} catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping(value = "/testUser")
    @PreAuthorize("hasRole('USER')")
    public String testUser(){
        return "USER WORKS";
    }

    @GetMapping(value = "/testAdmin")
    @PreAuthorize("hasRole('EDITOR')")
    public String testAdmin(){
        return "ADMIN WORKS";
    }
}
