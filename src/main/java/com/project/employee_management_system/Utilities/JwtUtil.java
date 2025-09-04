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
    private final String Secret = "Secrestshh!";
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
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SecurityKey)
                    .build()
                    .parseClaimsJwt(token)
                    .getBody()
                    .getSubject();
            return true;
        }
        catch (JwtException jwtE) {
            return false;
        }
    }
}
