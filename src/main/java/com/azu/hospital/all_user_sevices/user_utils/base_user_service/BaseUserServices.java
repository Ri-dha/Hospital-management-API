package com.azu.hospital.all_user_sevices.user_utils.base_user_service;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.token.DeleteAllTokenServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseUserServices {
    private final BaseUserDao baseUserDao;
    private final BaseUserDtoMapper mapper;
    private final DeleteAllTokenServices deleteAllTokenServices;

    public BaseUserServices(@Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
                            BaseUserDtoMapper mapper, DeleteAllTokenServices deleteAllTokenServices) {
        this.baseUserDao = baseUserDao;
        this.mapper = mapper;
        this.deleteAllTokenServices = deleteAllTokenServices;

    }

    public BaseUserDto getUserByToken(String token) {
        BaseUser user = baseUserDao.findByToken(token)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found"));
        return mapper.apply(user);
    }

    public void blockUserAccount(Long userId) {
        BaseUser user = baseUserDao.findById(userId)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found")

                );

        if (user.getEnabled() && user.getAccountNonLocked()) {
            user.setEnabled(false);
            user.setAccountNonLocked(false);
            user.setAccountNonExpired(false);
            user.setCredentialsNonExpired(false);

            baseUserDao.updateBaseUser(user);
            deleteAllTokenServices.deleteAllTokens(user.getId());
        }

    }

    public void unBlockUserAccount(Long userId) {
        BaseUser user = baseUserDao.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found")

                );

        if (!user.getEnabled() && !user.getAccountNonLocked()) {
            user.setEnabled(true);
            user.setAccountNonLocked(true);
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);
        }
        baseUserDao.updateBaseUser(user);
    }

    public BaseUserDto getDoctorById(Long doctorId) {

        return baseUserDao.findById(doctorId)
                .map(mapper)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User does not exist"
                        )
                );
    }


    public Page<BaseUserDto> getListOfUsersByRoles(List<Integer> roleIds, Pageable pageable) {
        Page<BaseUser> baseUserPage = baseUserDao.getAllDoctorsByRole(roleIds, pageable);
        List<BaseUserDto> baseUserList = baseUserPage
                .getContent()
                .stream()
                .map(mapper)
                .toList();

        return new PageImpl<>(baseUserList, pageable, baseUserPage.getTotalElements());
    }

    public Page<BaseUserDto> getAllUsers(String search, String roleName, Pageable pageable) {

        List<BaseUser> baseUserPage = baseUserDao.findAllUsersBy(search, roleName, pageable);
        List<BaseUserDto> baseUserList = baseUserPage
                .stream()
                .map(mapper)
                .toList();

        long count = baseUserDao.countAll();
        return new PageImpl<>(baseUserList, pageable, count);

    }




}

