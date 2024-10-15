package com.azu.hospital.security.newsecurity.jwt;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Set;

@Builder
public record AuthenticationResponse(
        String username,
        String email,
        @JsonProperty("access_token")
        String access_token,
//        @JsonProperty("refresh_token")
//        String refresh_token,
        Set<Role> role
) {

}
