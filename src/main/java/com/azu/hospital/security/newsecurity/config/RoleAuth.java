package com.azu.hospital.security.newsecurity.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RoleAuth {

    public boolean Trim(String... roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (String role : roles) {
            if (authorities.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase(role.trim()))) {
                return true;
            }
        }

        return false;
    }
}


