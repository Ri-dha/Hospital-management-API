package com.azu.hospital.exceptions.validators;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.enums.EnumUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("UserRole")
public class UserRoleValidator {

    private final Set<EnumUserRole> allowedRoles;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public UserRoleValidator(Set<EnumUserRole> allowedRoles, ExceptionsMessageReturn messageReturn) {
        this.messageReturn = messageReturn;
        this.allowedRoles = EnumSet.allOf(EnumUserRole.class);
    }

    public void validateRoles(Set<EnumRole> requestedRoles) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        for (EnumRole role : requestedRoles) {
            boolean isRoleAllowed = allowedRoles.stream()
                    .anyMatch(allowedRole -> allowedRole.getName().equals(role.getName()));

            if (!isRoleAllowed) {
                throw new BadRequestException(
                        messages.getString("unsuitableRolesAdd") + role.getName()
                );
            }
        }
    }
}
