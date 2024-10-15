package com.azu.hospital.all_user_sevices.roles_sevices;


import com.azu.hospital.utils.enums.EnumRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;


@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Boolean existsByName(String name);

    @Query("SELECT r FROM Role r WHERE r.name IN :names")
    Set<Role> findByNames(@Param("names") Set<String> names);


}
