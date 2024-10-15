package com.azu.hospital.security.auth;

import com.azu.hospital.security.newsecurity.token.TokenDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

    private final TokenDao tokenRepository;

    public LogoutService(TokenDao tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
//            storedToken.setExpire(true);
//            storedToken.setRevoke(true);
            tokenRepository.updateToken(storedToken);
            SecurityContextHolder.clearContext();
        }
    }


}
