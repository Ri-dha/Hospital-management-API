package com.azu.hospital.all_user_sevices.employee.nurses.services;

import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.exceptions.validators.NurseRoleValidator;
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

@Service("NurseUpdateService")
public class NurseUpdateService {

    private final NurseDao nurseDao;
    private final ImageService imageService;
    private final RoleService roleService;
    private final DeleteAllTokenServices deleteAllTokenServices;
    //    private final ChatUserDao chatUserDao;
    private final ExceptionsMessageReturn messageReturn;

    private final NurseRoleValidator nurseRoleValidator;

    private final PasswordEncoder passwordEncoder;


    public NurseUpdateService(
            @Qualifier("NurseJpa") NurseDao nurseDao,
            ImageService imageService, RoleService roleService,
            DeleteAllTokenServices deleteAllTokenServices,
//            @Qualifier("ChatUserDataAccessJpa") ChatUserDao chatUserDao,
            ExceptionsMessageReturn messageReturn, NurseRoleValidator nurseRoleValidator, PasswordEncoder passwordEncoder) {
        this.nurseDao = nurseDao;
        this.imageService = imageService;
        this.roleService = roleService;
        this.deleteAllTokenServices = deleteAllTokenServices;
//        this.chatUserDao = chatUserDao;
        this.messageReturn = messageReturn;
        this.nurseRoleValidator = nurseRoleValidator;
        this.passwordEncoder = passwordEncoder;
    }


    public void updateExistNurse(AllUserRegistrationRequest request, Long nurseId) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Nurse nurse = nurseDao.findNurseById(nurseId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound"))

                );
        nurseRoleValidator.validateRoles(request.getRoles());
        extractedCheckEachObjectUpdateMethodPrivate(request, nurse);
        nurseDao.updateCurrentNurse(nurse);


//        ChatUser chatUser = chatUserDao.findByUserId(userId);
//        chatUser.setName(nurse.getUsernameSpecial());
//        chatUser.setMobile(nurse.getMobile());
//        chatUser.setImage(nurse.getImage());
//        chatUser.setEmail(nurse.getEmail());
//        chatUserDao.updateChatUser(chatUser);

    }

    private void extractedCheckEachObjectUpdateMethodPrivate(AllUserRegistrationRequest request, Nurse nurse) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        boolean changes = false;
        if (request.getUsername() != null) {
            nurse.setUsername(request.getUsername());
            changes = true;
        }
        if (request.getPassword() != null) {
            nurse.setPassword(passwordEncoder.encode(request.getPassword()));
            deleteAllTokenServices.deleteAllTokens(nurse.getId());
            changes = true;
        }
        if (request.getMartialStatus() != null) {
            nurse.setMartialStatus(request.getMartialStatus());
            changes = true;
        }
        if (request.getGender() != null) {
            nurse.setGender(request.getGender());
            changes = true;
        }
        if (request.getEmail() != null && !Objects.equals(request.getEmail(), nurse.getEmail())) {
            nurse.setEmail(request.getEmail());
            deleteAllTokenServices.deleteAllTokens(nurse.getId());
            changes = true;
        }
        if (request.getMobile() != null) {
            nurse.setMobile(request.getMobile());
            changes = true;
        }
        if (request.getAddress() != null) {
            nurse.setAddress(request.getAddress());
            changes = true;
        }
        if (request.getBirthday() != null) {
            nurse.setBirthday(request.getBirthday());
            changes = true;
        }
        if (request.getEmployeeDate() != null) {
            nurse.setEmployeeDate(request.getEmployeeDate());
            changes = true;
        }
        if (request.getBloodGroup() != null) {
            nurse.setBloodGroup(request.getBloodGroup());
            changes = true;
        }
        if (request.getRoles() != null) {
            nurse.setRoles(roleService.findRolesByEnumSet(request.getRoles()));
            deleteAllTokenServices.deleteAllTokens(nurse.getId());
            changes = true;
        }
        if (request.getFrontPersonalId() != null) {
            nurse.setFrontPersonalId(imageService.saveImage(request.getFrontPersonalId()));
            changes = true;
        }
        if (request.getBackPersonalId() != null) {
            nurse.setBackPersonalId(imageService.saveImage(request.getBackPersonalId()));
            changes = true;
        }
        if (request.getFrontMedicalId() != null) {
            nurse.setFrontMedicalId(imageService.saveImage(request.getFrontMedicalId()));
            changes = true;
        }
        if (request.getBackMedicalId() != null) {
            nurse.setBackMedicalId(imageService.saveImage(request.getBackMedicalId()));
            changes = true;
        }
        if (request.getImage() != null) {
            nurse.setImage(imageService.saveImage(request.getImage()));
            changes = true;
        }
        if (!changes) {
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }
    }
}
