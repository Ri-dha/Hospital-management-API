package com.azu.hospital.all_user_sevices.user_utils.lookup;

import java.util.Optional;

public interface UserLookupStrategy {

    Optional<AppUser> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
