package com.azu.hospital.security.auth;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUserService;
import com.azu.hospital.exceptions.AuthenticationCredentialsNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.security.newsecurity.jwt.AuthenticationRequest;
import com.azu.hospital.security.newsecurity.jwt.AuthenticationResponse;
import com.azu.hospital.security.newsecurity.token.BasicTokenEnum;
import com.azu.hospital.security.newsecurity.token.Token;
import com.azu.hospital.security.newsecurity.token.TokenDao;
import com.azu.hospital.security.newsecurity.token.TokenTypeEnum;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class AuthenticationService {

    private final BaseUserDao baseUserDao;
    private final TokenDao tokenDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final HttpServletRequest servletRequest;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public AuthenticationService(
            @Qualifier("BaseUserJpa")BaseUserDao baseUserDao,
            @Qualifier("tokenJpa") TokenDao tokenDao,
            @Qualifier("jwt") JwtService jwtService,
            @Qualifier("AuthManger") AuthenticationManager authenticationManager,
            @Qualifier("AppUserService") AppUserService appUserService, HttpServletRequest servletRequest, ExceptionsMessageReturn messageReturn) {
        this.baseUserDao = baseUserDao;
        this.tokenDao = tokenDao;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        this.servletRequest = servletRequest;
        this.messageReturn = messageReturn;
    }




    @Transactional
    public AuthenticationResponse authenticate(
            @RequestHeader(value = "type") TokenTypeEnum type,
             AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        BaseUser authenticatedUser = baseUserDao.findByEmail(request.email())
                .orElseThrow(
                        () -> new UsernameNotFoundException
                                (
                                        messages.getString("resourceNotFound")
                                )
                );

        getAllValidTokes(type, authenticatedUser);
        deleteAllInValidTokens(type, authenticatedUser);
        getAllValidRefreshTokes(type, authenticatedUser);
        deleteAllInValidRefreshTokens(type, authenticatedUser);
        var jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getId() , authenticatedUser.getRoles());
        saveUserToken(authenticatedUser, jwtToken, type, BasicTokenEnum.Access_Token);

        return new AuthenticationResponse(
                authenticatedUser.getUsernameSpecial(),
                authenticatedUser.getEmail(),
                jwtToken,
                new HashSet<>(authenticatedUser.getRoles())
        );
    }

    private void getAllValidTokes(TokenTypeEnum type, BaseUser authenticatedUser) {
        List<Token> existingValidTokens = tokenDao.getAllValidTokens(authenticatedUser.getId(), type);
        tokenTypeCheck(type, existingValidTokens);
    }

    private void tokenTypeCheck(TokenTypeEnum type, List<Token> existingValidTokens) {
        if (TokenTypeEnum.PC == type){
            if (!existingValidTokens.isEmpty()){
                for (Token token : existingValidTokens){
//                    token.setRevoke(true);
//                    token.setExpire(true);
                    tokenDao.updateToken(token);
                }
            }
        }else if (TokenTypeEnum.MOBILE == type){
            if (!existingValidTokens.isEmpty()){
                for (Token token : existingValidTokens){
//                    token.setRevoke(true);
//                    token.setExpire(true);
                    tokenDao.updateToken(token);
                }
            }
        }
    }

    private void deleteAllInValidTokens(TokenTypeEnum type, BaseUser authenticatedUser) {
        List<Token> existingIValidTokens = tokenDao.getAllInValidTokens(authenticatedUser.getId(), type);
        tokenTypeDeleteChecks(type, existingIValidTokens);
    }

    private void tokenTypeDeleteChecks(TokenTypeEnum type, List<Token> existingIValidTokens) {
        if (TokenTypeEnum.PC == type){
            if (!existingIValidTokens.isEmpty()){
                for (Token token : existingIValidTokens){
                    tokenDao.deleteToken(token);
                }
            }
        }else if (TokenTypeEnum.MOBILE == type){
            if (!existingIValidTokens.isEmpty()){
                for (Token token : existingIValidTokens){
                    tokenDao.deleteToken(token);
                }
            }
        }
    }

    private void getAllValidRefreshTokes(TokenTypeEnum type, BaseUser authenticatedUser) {
        List<Token> existingValidTokens = tokenDao.getAllValidRefreshTokens(authenticatedUser.getId(), type);
        tokenTypeCheck(type, existingValidTokens);
    }

    private void deleteAllInValidRefreshTokens(TokenTypeEnum type, BaseUser authenticatedUser) {
        List<Token> existingIValidTokens = tokenDao.getAllInValidRefreshTokens(authenticatedUser.getId(), type);
        tokenTypeDeleteChecks(type, existingIValidTokens);
    }

    private void saveUserToken(BaseUser user, String jwtToken, TokenTypeEnum type, BasicTokenEnum tokenType) {
        var token = Token.builder()
                .users(user)
                .token(jwtToken)
//                .isExpire(false)
//                .isRevoke(false)
                .tokenType(type)
                .basicTokenEnum(tokenType)
                .build();
        tokenDao.createNewToken(token);
    }



    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestHeader("type") TokenTypeEnum type
    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        Locale locale = messageReturn.getMessageLocally();
//        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//
//        if (userEmail != null) {
//            var userDetails = this.baseUserDao.findByEmail(userEmail)
//                    .orElseThrow(
//                            () -> new AuthenticationCredentialsNotFoundException(
//                                    messages.getString("resourceNotFound")
//                            )
//                    );
//            var isTokenValid = tokenDao.findByToken(refreshToken)
//                    .map(t -> !t.isExpire() || !t.isRevoke())
//                    .orElseThrow(
//                            () -> new AuthenticationCredentialsNotFoundException(
//                                    messages.getString("notExistingRoles")
//                            )
//                    );
//
//            if (jwtService.isTokenValid(refreshToken, userDetails) && isTokenValid) {
//                getAllValidTokes(type, userDetails);
//                deleteAllInValidTokens(type, userDetails);
//               var accessToken = jwtService.generateToken(userDetails, userDetails.getId(), userDetails.getRoles());
//                saveUserToken(userDetails, accessToken, type, BasicTokenEnum.Access_Token);
//               var authResponse = AuthenticationResponse
//                       .builder()
//                       .access_token(accessToken)
////                       .refresh_token(refreshToken)
//                       .username(userDetails.getUsernameSpecial())
//                       .email(userDetails.getUsername())
//                       .role(userDetails.getRoles())
//                       .build();
//               new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
    }



    public void userLogOut(
            @RequestHeader(value = "type") TokenTypeEnum type
    ){
        final String authHeader = servletRequest.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenDao.findByToken(jwt)
                .orElse(null);
       BaseUser user =  baseUserDao.findByToken(jwt)
               .orElse(null);

        if (storedToken != null && user != null) {
            getAllValidTokes(type, user);
            deleteAllInValidTokens(type, user);
            getAllValidRefreshTokes(type,user);
            deleteAllInValidRefreshTokens(type, user);
            SecurityContextHolder.clearContext();
        }
    }


}
