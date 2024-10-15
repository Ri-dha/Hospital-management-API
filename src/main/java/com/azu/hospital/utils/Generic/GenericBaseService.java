package com.azu.hospital.utils.Generic;

import com.azu.hospital.exceptions.AuthenticationCredentialsNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericBaseService {

    private final HttpServletRequest request;

    private final JwtService jwtService;
    private Long id;
    private List<String> roles = new ArrayList<>();

    public GenericBaseService(JwtService jwtService, HttpServletRequest request) {
        this.jwtService = jwtService;
        this.request = request;
    }

    public Long authId() {

        try {
            Claims claims = jwtService.extractAllClaimsFromToken(request);
            return Long.valueOf(claims.get("userId").toString());
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException(
                    "Un Authorize"
            );
        }

    }

    public List<String> authRoles() {

        try {
            Claims claims = jwtService.extractAllClaimsFromToken(request);

            if (claims.get("roles") instanceof List) {
                return (List<String>) claims.get("roles");
            }

        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException(
                    "Un Authorize"
            );
        }
        return null;
    }


    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
