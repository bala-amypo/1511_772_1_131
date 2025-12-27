package com.example.demo.security;

import com.example.demo.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

public class JwtTokenProvider {

    // ✅ Base64-encoded secret key (REQUIRED for JJWT 0.11+)
    private static final String SECRET =
            Base64.getEncoder().encodeToString(
                    "smart-grid-load-shedding-secret-key-123456".getBytes()
            );

    private static final long VALIDITY_MS = 3600000; // 1 hour

    // ✅ Proper HMAC key
    private final SecretKey key =
            Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

    // =================================================
    // CREATE TOKEN (USED BY TESTS)
    // =================================================
    public String createToken(AppUser user) {

        return Jwts.builder()
                .setSubject(user.getEmail())     // ✅ test expects subject = email
                .claim("role", user.getRole())  // ✅ test expects role claim
                .claim("userId", user.getId())  // ✅ test expects userId claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // =================================================
    // VALIDATE TOKEN (USED BY TESTS)
    // =================================================
    public boolean validateToken(String token) {
        getClaims(token); // will throw exception if invalid
        return true;
    }

    // =================================================
    // GET CLAIMS (USED BY TESTS)
    // =================================================
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // =================================================
    // USED BY JwtAuthenticationFilter
    // =================================================
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }
}
