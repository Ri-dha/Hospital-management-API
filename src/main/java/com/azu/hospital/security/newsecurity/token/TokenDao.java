package com.azu.hospital.security.newsecurity.token;

import java.util.List;
import java.util.Optional;

public interface TokenDao {

    List<Token> findAllInvalidToken(Long userId);

    List<Token> getAllValidTokens(Long userId, TokenTypeEnum tokenType);

    List<Token> getAllInValidTokens(Long userId, TokenTypeEnum tokenType);

    List<Token> getAllValidRefreshTokens(Long userId, TokenTypeEnum tokenType);

    List<Token> getAllInValidRefreshTokens(Long userId, TokenTypeEnum tokenType);

    Optional<Token> findByToken(String token);

    Token createNewToken(Token request);

    void deleteToken(Token token);

    Optional<Token> getUserToken(Long userId);

    void updateToken(Token update);

    List<Token> getTokensByEmail(String email);

    void deleteTokenByEmail();

    void deleteTokenById(Long id);


    List<Token> getAllTokenByUserId(Long userId);


}
