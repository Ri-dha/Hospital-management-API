package com.azu.hospital.security.newsecurity.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("tokenJpa")
public class TokenJPADataAccess implements TokenDao{

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenJPADataAccess(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public List<Token> findAllInvalidToken(Long userId) {
        return new ArrayList<>(tokenRepository.findAllInvalidToken(userId));
    }

    @Override
    public List<Token> getAllValidTokens(Long userId, TokenTypeEnum tokenType) {
        return tokenRepository.getAllValidTokens(userId, tokenType);

    }

    @Override
    public List<Token> getAllInValidTokens(Long userId, TokenTypeEnum tokenType) {
        return tokenRepository.getAllInValidTokens(userId, tokenType);
    }

    @Override
    public List<Token> getAllValidRefreshTokens(Long userId, TokenTypeEnum tokenType) {
        return tokenRepository.getAllValidRefreshTokens(userId, tokenType);
    }

    @Override
    public List<Token> getAllInValidRefreshTokens(Long userId, TokenTypeEnum tokenType) {
        return tokenRepository.getAllInValidRefreshTokens(userId, tokenType);
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public Token createNewToken(Token request) {
        return tokenRepository.save(request);
    }

    @Override
    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }

    @Override
    public Optional<Token> getUserToken(Long userId) {
        return tokenRepository.findTokenByUserId(userId);
    }

    @Override
    public void updateToken(Token update) {
        tokenRepository.save(update);
    }

    @Override
    public List<Token> getTokensByEmail(String email) {
        return tokenRepository.findAllByUserEmail(email);
    }

    @Override
    public void deleteTokenByEmail() {
//        tokenRepository.deleteAllRevokeTokens();
    }

    @Override
    public void deleteTokenById(Long id) {
        tokenRepository.deleteById(id);
    }



    @Override
    public List<Token> getAllTokenByUserId(Long userId) {
        return tokenRepository.findAllByUsersId(userId);
    }


}
