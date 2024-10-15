package com.azu.hospital.security.audting;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditingAware implements AuditorAware<Long> {

    @Override
    public @NotNull Optional<Long> getCurrentAuditor() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext().getAuthentication();
        if (
                    authentication == null
                    || !authentication.isAuthenticated()
                    || authentication instanceof AnonymousAuthenticationToken
        ){
            return Optional.empty();
        }

        BaseUser userResponsible = (BaseUser) authentication.getPrincipal();

        return Optional.ofNullable(userResponsible.getId());
    }
}
