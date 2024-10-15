package com.azu.hospital.all_user_sevices.employee.doctors.services;

import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUser;
import com.azu.hospital.all_user_sevices.user_utils.lookup.UserLookupStrategy;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Service("DoctorAppUser")
public class DoctorUserLookupStrategy implements UserLookupStrategy {

    private final DoctorDao dao;
    private final ExceptionsMessageReturn messageReturn;


    public DoctorUserLookupStrategy(DoctorDao dao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.messageReturn = messageReturn;
    }

    @Override
    public Optional<AppUser> findUserByEmail(String email) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return Optional.ofNullable(dao.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                ));
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return dao.existsByEmail(email);
    }
}
