package com.project.employee_management_system.Utilities;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String Secret = "mysupersecretkeymysupersecretkey1234";
    private final long Expiration = 1000 * 60 * 60 * 24; // Validity of a token
    private final Key SecurityKey = Keys.hmacShaKeyFor(Secret.getBytes(StandardCharsets.UTF_8));
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(SecurityKey, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SecurityKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateJwtToken(String token) {
        try {
            extractEmail(token);
            return true;
        }
        catch (JwtException jwtE) {
            System.out.println("JWT validation failed: " + jwtE.getMessage());
            return false;
        }
    }
}