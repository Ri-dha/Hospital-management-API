package com.azu.hospital.all_user_sevices.user_utils.lookup;

import com.azu.hospital.exceptions.EmailAlreadyExistsException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Service("AppUserService")
public class AppUserService {

    private final List<UserLookupStrategy> strategies;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public AppUserService(List<UserLookupStrategy> strategies, ExceptionsMessageReturn messageReturn) {
        this.strategies = strategies;
        this.messageReturn = messageReturn;
    }

    public AppUser findAppUserByEmail(String email) {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        for (UserLookupStrategy strategy : strategies) {
            Optional<AppUser> appUser = strategy.findUserByEmail(email);
            if (appUser.isPresent()) {
                return appUser.get();
            }
        }
        throw new UsernameNotFoundException(
                messages.getString("userNotFound")
        );
    }

    public void checkIfEmailExists(String email) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        for (UserLookupStrategy strategy : strategies) {
            boolean appUser = strategy.existsUserByEmail(email);
            if (appUser) {
                throw new EmailAlreadyExistsException(
                        messages.getString("existEmail") + email
                );
            }
        }
    }
}
