package com.mnit.erp.security;

import com.mnit.erp.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY_FOR_SYSTEM_USER = 5 * 60 * 60;
    public static final long JWT_TOKEN_VALIDITY_FOR_MOBILE_USER = 1 * 60 * 60 * 24 * 365;
    private String secret = "acde50b4-8468-11ea-bc55-0242ac130003";

    public String getUsernameFromToken(String token) {
        return getClaimFromTokenForSubject(token);
    }

    private String getClaimFromTokenForSubject(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromTokenForExpDate(token);
    }

    private Date getClaimFromTokenForExpDate(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails);
    }

    private String doGenerateToken(Map<String, Object> claims, User userDetails) {
        Date expiration = new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY_FOR_SYSTEM_USER * 1000));
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}