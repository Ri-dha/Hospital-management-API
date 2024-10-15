package com.azu.hospital.all_user_sevices.user_utils;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.utils.Generic.GenericBaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class GetUserNameForAuditing extends GenericBaseService {

    private final BaseUserDao baseUserDao;
    public GetUserNameForAuditing(JwtService jwtService, HttpServletRequest request, BaseUserDao baseUserDao) {
        super(jwtService, request);
        this.baseUserDao = baseUserDao;
    }

    public String providerName() {
        return baseUserDao.findById(authId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + authId() + " does not exist")
        ).getUsernameSpecial();
    }



    public String getProviderName(Long id) {
        return baseUserDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " does not exist")
        ).getUsernameSpecial();
    }
}
