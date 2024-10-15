package com.azu.hospital.exceptions.validators;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.EnumPermanentRole;
import com.azu.hospital.utils.enums.EnumPermanentRoleValidator;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

@Component("PermanentRoleValidator")
public class PermanentRoleValidator {
    private final Set<EnumPermanentRoleValidator> allowedRoles;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PermanentRoleValidator(Set<EnumPermanentRoleValidator> allowedRoles1, ExceptionsMessageReturn messageReturn) {
        this.allowedRoles = allowedRoles1;
        this.messageReturn = messageReturn;
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
