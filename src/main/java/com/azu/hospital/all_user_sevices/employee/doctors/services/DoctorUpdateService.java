package com.azu.hospital.all_user_sevices.employee.doctors.services;

import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.exceptions.validators.DoctorRoleValidator;
import com.azu.hospital.security.newsecurity.token.DeleteAllTokenServices;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Service("DoctorUpdateService")
public class DoctorUpdateService {

    private final DoctorDao doctorDao;
    private final ImageService imageService;
    private final RoleService roleService;
//    private final ChatUserDao chatUserDao;
    private final DeleteAllTokenServices deleteAllTokenServices;
    private final ExceptionsMessageReturn messageReturn;
    private final DoctorRoleValidator roleValidator;
    private final PasswordEncoder passwordEncoder;


    public DoctorUpdateService(
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            ImageService imageService, RoleService roleService,
//            @Qualifier("ChatUserDataAccessJpa") ChatUserDao chatUserDao,
//            @Qualifier("UpdateChatUserService") IUpdateChatUserService updateChatUserService,
            DeleteAllTokenServices deleteAllTokenServices, ExceptionsMessageReturn messageReturn, DoctorRoleValidator roleValidator, PasswordEncoder passwordEncoder) {
        this.doctorDao = doctorDao;
        this.imageService = imageService;
        this.roleService = roleService;
//        this.chatUserDao = chatUserDao;

        this.deleteAllTokenServices = deleteAllTokenServices;
        this.messageReturn = messageReturn;
        this.roleValidator = roleValidator;
        this.passwordEncoder = passwordEncoder;
    }


    public void updateExistDoctor(AllUserRegistrationRequest request, Long doctorId) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Doctor doctor = doctorDao.findDoctorById(doctorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )

                );
        roleValidator.validateRoles(request.getRoles());

        extractedCheckEachObjectUpdateMethodPrivate(request, doctor);
        doctorDao.updateCurrentDoctor(doctor);

//        ChatUser chatUser = chatUserDao.findByUserId(doctorId);
//        chatUser.setName(doctor.getUsernameSpecial());
//        chatUser.setMobile(doctor.getMobile());
//        chatUser.setImage(doctor.getImage());
//        chatUser.setEmail(doctor.getEmail());
//        chatUserDao.updateChatUser(chatUser);


    }

    private void extractedCheckEachObjectUpdateMethodPrivate(AllUserRegistrationRequest request, Doctor doctor) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        boolean changes = false;
        if (request.getUsername() != null) {
            doctor.setUsername(request.getUsername());
            changes = true;
        }
        if (request.getPassword() != null) {
            doctor.setPassword(passwordEncoder.encode(request.getPassword()));
            deleteAllTokenServices.deleteAllTokens(doctor.getId());
            changes = true;
        }
        if (request.getMartialStatus() != null) {
            doctor.setMartialStatus(request.getMartialStatus());
            changes = true;
        }
        if (request.getGender() != null) {
            doctor.setGender(request.getGender());
            changes = true;
        }
        if (request.getEmail() != null && !Objects.equals(request.getEmail(), doctor.getEmail())) {
            doctor.setEmail(request.getEmail());
            deleteAllTokenServices.deleteAllTokens(doctor.getId());
            changes = true;
        }
        if (request.getMobile() != null) {
            doctor.setMobile(request.getMobile());
            changes = true;
        }
        if (request.getAddress() != null) {
            doctor.setAddress(request.getAddress());
            changes = true;
        }
        if (request.getBirthday() != null) {
            doctor.setBirthday(request.getBirthday());
            changes = true;
        }
        if (request.getEmployeeDate() != null) {
            doctor.setEmployeeDate(request.getEmployeeDate());
            changes = true;
        }
        if (request.getBloodGroup() != null) {
            doctor.setBloodGroup(request.getBloodGroup());
            changes = true;
        }

        if (request.getRoles() != null) {
            doctor.setRoles(roleService.findRolesByEnumSet(request.getRoles()));
            deleteAllTokenServices.deleteAllTokens(doctor.getId());
            changes = true;
        }
        if (request.getSubSpecialty() != null) {
            doctor.setSubSpecialist(request.getSubSpecialty());
            changes = true;
        }
        if (request.getFrontPersonalId() != null) {
            doctor.setFrontPersonalId(imageService.saveImage(request.getFrontPersonalId()));
            changes = true;
        }
        if (request.getBackPersonalId() != null) {
            doctor.setBackPersonalId(imageService.saveImage(request.getBackPersonalId()));
            changes = true;
        }
        if (request.getFrontMedicalId() != null) {
            doctor.setFrontMedicalId(imageService.saveImage(request.getFrontMedicalId()));
            changes = true;
        }
        if (request.getBackMedicalId() != null) {
            doctor.setBackMedicalId(imageService.saveImage(request.getBackMedicalId()));
            changes = true;
        }
        if (request.getImage() != null) {
            doctor.setImage(imageService.saveImage(request.getImage()));
            changes = true;
        }
//        if (request.getAppointmentNumber() != null) {
//            doctor.setAppointmentNumber(request.getAppointmentNumber());
//            changes = true;
//        }

        if (!changes) {
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }
    }
}
