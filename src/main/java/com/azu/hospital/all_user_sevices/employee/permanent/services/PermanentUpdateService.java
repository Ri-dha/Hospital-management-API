package com.azu.hospital.all_user_sevices.employee.permanent.services;

import com.azu.hospital.all_user_sevices.employee.permanent.dao.PermanentDao;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;

import com.azu.hospital.exceptions.ResourceNotFoundException;
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

@Service("PermanentUpdateService")
public class PermanentUpdateService {

    private final PermanentDao permanentDao;
    private final ImageService imageService;
    private final DeleteAllTokenServices deleteAllTokenServices;
    private final RoleService roleService;

//    private final ChatUserDao chatUserDao;
    private final ExceptionsMessageReturn messageReturn;
    private final PasswordEncoder passwordEncoder;


    public PermanentUpdateService(
            @Qualifier("PermanentJpa") PermanentDao permanentDao,
            ImageService imageService, DeleteAllTokenServices deleteAllTokenServices, RoleService roleService,
//            @Qualifier("ChatUserDataAccessJpa") ChatUserDao chatUserDao,
            ExceptionsMessageReturn messageReturn,
            PasswordEncoder passwordEncoder) {
        this.permanentDao = permanentDao;
        this.imageService = imageService;
        this.deleteAllTokenServices = deleteAllTokenServices;
        this.roleService = roleService;
//        this.chatUserDao = chatUserDao;
        this.messageReturn = messageReturn;
        this.passwordEncoder = passwordEncoder;
    }



    public void updateExistPermanent(AllUserRegistrationRequest request, Long permanentId) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Permanent permanent = permanentDao.findPermanentById(permanentId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound"))

                );

        extractedCheckEachObjectUpdateMethodPrivate(request, permanent);
        permanentDao.updateCurrentPermanent(permanent);

//        ChatUser chatUser = chatUserDao.findByUserId(permanentId);
//        chatUser.setName(permanent.getUsernameSpecial());
//        chatUser.setMobile(permanent.getMobile());
//        chatUser.setImage(permanent.getImage());
//        chatUser.setEmail(permanent.getEmail());
//        chatUserDao.updateChatUser(chatUser);

    }


    private void extractedCheckEachObjectUpdateMethodPrivate(AllUserRegistrationRequest request, Permanent permanent) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        boolean changes = false;
        if (request.getUsername() != null){
            permanent.setUsername(request.getUsername());
            changes = true;
        }if (request.getPassword() != null){
            permanent.setPassword(passwordEncoder.encode(request.getPassword()));
            deleteAllTokenServices.deleteAllTokens(permanent.getId());
            changes = true;
        }if (request.getMartialStatus() != null){
            permanent.setMartialStatus(request.getMartialStatus());
            changes = true;
        }if (request.getGender() != null){
            permanent.setGender(request.getGender());
            changes = true;
        }if (request.getEmail() != null && !Objects.equals(request.getEmail(), permanent.getEmail())){
            permanent.setEmail(request.getEmail());
            deleteAllTokenServices.deleteAllTokens(permanent.getId());
            changes = true;
        }if (request.getMobile() != null){
            permanent.setMobile(request.getMobile());
            changes = true;
        }if (request.getAddress() != null){
            permanent.setAddress(request.getAddress());
            changes = true;
        }if (request.getBirthday() != null){
            permanent.setBirthday(request.getBirthday());
            changes = true;
        }if (request.getEmployeeDate() != null){
            permanent.setEmployeeDate(request.getEmployeeDate());
            changes = true;
        }if (request.getBloodGroup() != null){
            permanent.setBloodGroup(request.getBloodGroup());
            changes = true;
        }if (request.getRoles() != null){
            permanent.setRoles(roleService.findRolesByEnumSet(request.getRoles()));
            deleteAllTokenServices.deleteAllTokens(permanent.getId());
            changes = true;
        }if (request.getFrontPersonalId() != null){
            permanent.setFrontPersonalId(imageService.saveImage(request.getFrontPersonalId()));
            changes = true;
        }
        if (request.getBackPersonalId() != null){
            permanent.setBackPersonalId(imageService.saveImage(request.getBackPersonalId()));
            changes = true;
        }
        if (request.getFrontMedicalId() != null){
            permanent.setFrontMedicalId(imageService.saveImage(request.getFrontMedicalId()));
            changes = true;
        }
        if (request.getBackMedicalId() != null){
            permanent.setBackMedicalId(imageService.saveImage(request.getBackMedicalId()));
            changes = true;
        }if (request.getImage() != null){
            permanent.setImage(imageService.saveImage(request.getImage()));
            changes = true;
        }
        if (!changes){
          throw new ResourceNotFoundException(
                    messages.getString("noChanges")
          );
        }
    }
}
