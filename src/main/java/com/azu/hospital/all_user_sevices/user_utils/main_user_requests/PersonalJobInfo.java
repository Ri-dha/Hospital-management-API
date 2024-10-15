package com.azu.hospital.all_user_sevices.user_utils.main_user_requests;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.utils.enums.EnumRole;

import java.math.BigDecimal;
import java.util.Set;

public interface PersonalJobInfo {
    String getBloodGroup();

    void setBloodGroup(String bloodGroup);

    Set<EnumRole> getRoles();

    void setRoles(Set<EnumRole> roles);

    String getSpecialty();

    void setSpecialty(String specialist);

    String getSubSpecialty();

    void setSubSpecialty(String subSpecialty);
}
