package com.azu.hospital.security.newsecurity.token;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/token")
public class TokenController {

    private final TokenDao tokenDao;

    public TokenController(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvalid(@PathVariable("id") Long id){
        List<Token> tokens = tokenDao.findAllInvalidToken(id);
        return ResponseEntity.ok(tokens);
    }
}
