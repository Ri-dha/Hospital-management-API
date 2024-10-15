package com.azu.hospital.security.checking;

import com.azu.hospital.security.newsecurity.config.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

@Component
public class ExtractRolesFromToken {

    @Value("${app.jwt.secret}")
    private String secretKey;

    private final JwtService jwt;
    private final HttpServletRequest request;

    @Autowired
    public ExtractRolesFromToken(JwtService jwt, HttpServletRequest request) {
        this.jwt = jwt;
        this.request = request;
    }

    public String getToken(){
        String authHeader = this.request.getHeader("Authorization");
        return authHeader.substring(7);
    }



    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public Claims extractAllClaims() {
        String token = getToken();
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public List<Object> getRoles(){
        return List.of(extractAllClaims().get("roles"));
    }
}
