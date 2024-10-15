package com.azu.hospital.security.newsecurity.jwt;

public record AuthenticationRequest(
        String username,

        String email,

        String password

) {
}
