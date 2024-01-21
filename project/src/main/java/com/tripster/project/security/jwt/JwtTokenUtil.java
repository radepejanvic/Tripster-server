package com.tripster.project.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 365L * 24L * 60L * 60L * 1000L;
    @Value("${jwt.key}")
    private String key;

    public Date getExpirationFormToken(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public String getUsernameFormToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationFormToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return doGenerateToken(claims,userDetails);
    }
    private String doGenerateToken(Map<String,Object> claims,UserDetails userDetails){
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .setIssuedAt( new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512,key).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFormToken(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token)
                && userDetails.isEnabled());
    }
}

