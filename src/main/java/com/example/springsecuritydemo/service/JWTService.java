package com.example.springsecuritydemo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private final String secretKey;

    public JWTService() {
        this.secretKey = generateKey();
        System.out.println("Generated Secret Key: " + secretKey);
    }

    private String generateKey(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error Generating Secret Key", e);
        }
    }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        int expiration = 1000 * 60 * 60; // 1 hour

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    private Key getSignInKey() {
        byte[] getbytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(getbytes);
    }
}
