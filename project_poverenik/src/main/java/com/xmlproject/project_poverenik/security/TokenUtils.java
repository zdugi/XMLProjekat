package com.xmlproject.project_poverenik.security;


import com.xmlproject.project_poverenik.model.xml_korisnik.Korisnik;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    @Value("TicketServe")
    private String APP_NAME;

    @Value("SomeThingWeKnow")
    private String SECRET;

    @Value("50000")
    private int EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;

    @Value("User-role")
    private String ROLES_CLAIM_NAME;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public static Date now(){
        return new Date();
    }

    public String generateToken(Korisnik user) {

        return Jwts.builder()
                .claim(ROLES_CLAIM_NAME, user.getAutorizacijaInformacije().getRole())
                .setIssuer(APP_NAME)
                .setSubject(user.getAutorizacijaInformacije().getUsername())
                .setIssuedAt(new Date())
                .setExpiration(generateExpiration())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private Date generateExpiration(){
        return new Date( now().getTime() + EXPIRES_IN * 1000);
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Claims getAllClaimsFromTokenIgnoreExp(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch(ExpiredJwtException e) {
            claims = e.getClaims();
        }catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public int getExpiredIn() {
        return EXPIRES_IN;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String getUsernameFromTokenIgnoreExp(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromTokenIgnoreExp(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    // Functions for getting JWT token out of HTTP request

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public int getExpired() {
        return EXPIRES_IN;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }
}
