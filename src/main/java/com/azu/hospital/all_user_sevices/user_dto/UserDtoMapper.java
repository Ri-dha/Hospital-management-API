package com.azu.hospital.all_user_sevices.user_dto;

import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.token.Token;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.function.Function;
@Service
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getGender(),
                user.getMartialStatus(),
                user.getImage(),
                user.getEmail(),
                user.getMobile(),
                user.getAddress(),
                user.getBirthday(),
                user.getEmployeeDate(),
                user.getBloodGroup(),

                user.getRolesSpecial(),
                user.getSpecialist(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                user.getFrontPersonalId(),
                user.getBackPersonalId(),
                user.getFrontMedicalId(),
                user.getBackMedicalId()
        );
    }
}

