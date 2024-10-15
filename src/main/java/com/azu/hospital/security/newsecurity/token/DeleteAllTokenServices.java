package com.azu.hospital.security.newsecurity.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DeleteAllTokenServices {

    private final TokenDao tokenDao;

    @Autowired
    public DeleteAllTokenServices(@Qualifier("tokenJpa") TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }


    public void deleteAllTokens(Long userId){
        List<Token> tokens = tokenDao.getAllTokenByUserId(userId);
        if (!tokens.isEmpty()) {
            for (Token token : tokens) {
                tokenDao.deleteToken(token);
            }
        }
    }
}
