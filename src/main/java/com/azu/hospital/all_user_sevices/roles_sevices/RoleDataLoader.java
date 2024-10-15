package com.azu.hospital.all_user_sevices.roles_sevices;

import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Component
public class RoleDataLoader implements ApplicationRunner {


    private final RoleRepository roleRepository;

    @Autowired
    public RoleDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    @Transactional

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.count() == 0) {
            for (EnumRole role : EnumRole.values()) {
                Role roleEntity = new Role();
                roleEntity.setName(String.valueOf(role));
                roleRepository.save(roleEntity);
            }
        }
    }



}
