package com.chaozusTracker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretKey));
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +this.expirationTime))
                .signWith(this.getSignKey())
                .compact();
    }

    public boolean validateToken(String token){
        try{
            JwtParser parser = Jwts.parser()
                    .setSigningKey(getSignKey())
                    .build();
            parser.parseEncryptedClaims(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String extractEmail( String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(this.getSignKey())
                .build();

        Claims claims = parser .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
