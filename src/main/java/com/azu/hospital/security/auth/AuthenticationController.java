package com.azu.hospital.security.auth;


import com.azu.hospital.security.newsecurity.jwt.AuthenticationRequest;
import com.azu.hospital.security.newsecurity.token.TokenTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;



@RestController
@RequestMapping("api/v1/new_auth")
@CrossOrigin()
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login", consumes = "multipart/form-data")
    public ResponseEntity<?> authenticate(
            @RequestHeader(value = "type") TokenTypeEnum type,
            @ModelAttribute AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(type, request ));
    }


    @PostMapping(value = "/logout")
    @ResponseStatus(value = HttpStatus.OK, reason = "User Logout")
    public void userLogout(
            @RequestHeader(value = "type") TokenTypeEnum type
    ){
        authenticationService.userLogOut(type);
    }


    @PostMapping(value = "/refresh-token")
    public void refreshTokens(
            @RequestHeader(value = "type") TokenTypeEnum type,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response, type);
    }


}
