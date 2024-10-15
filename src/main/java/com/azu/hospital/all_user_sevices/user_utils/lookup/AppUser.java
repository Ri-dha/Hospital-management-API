package com.azu.hospital.all_user_sevices.user_utils.lookup;


import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.utils.enums.EnumRole;
import java.util.Set;

public interface AppUser {
    Long getId();
    String getEmail();
    String getUsername();
    String getPassword();
    Set<Role> getRoles();
}
