package com.azu.hospital.all_user_sevices.user_sevices;

import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;
import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.all_user_sevices.user_entity.User;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.token.DeleteAllTokenServices;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Service("UserUpdateService")
public class UserUpdateService {

    private final UserDao userDao;
    private final ImageService imageService;
    private final RoleService roleService;
    private final DeleteAllTokenServices deleteAllTokenServices;

//    private final ChatUserDao chatUserDao;
    private final ExceptionsMessageReturn messageReturn;
    private final PasswordEncoder passwordEncoder;


    public UserUpdateService(
            @Qualifier("UserJpa") UserDao userDao,
            ImageService imageService,
            RoleService roleService,
            DeleteAllTokenServices deleteAllTokenServices,
//            @Qualifier("ChatUserDataAccessJpa") ChatUserDao chatUserDao,
            ExceptionsMessageReturn messageReturn, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.imageService = imageService;
        this.roleService = roleService;
        this.deleteAllTokenServices = deleteAllTokenServices;
//        this.chatUserDao = chatUserDao;
        this.messageReturn = messageReturn;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void updateExistUser(AllUserRegistrationRequest request, Long userId) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        User user = userDao.findUserById(userId)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                messages.getString("resourceNotFound")
                        )

                );

        privateCheckEachItemMethod(request, user);
        userDao.updateCurrentUser(user);



//        ChatUser chatUser = chatUserDao.findByUserId(userId);
//        chatUser.setName(user.getUsernameSpecial());
//        chatUser.setMobile(user.getMobile());
//        chatUser.setImage(user.getImage());
//        chatUser.setEmail(user.getEmail());
//        chatUserDao.updateChatUser(chatUser);

    }

    private void privateCheckEachItemMethod(AllUserRegistrationRequest request, User user) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        boolean userUpdatechanges = false;
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());

            userUpdatechanges = true;
        }
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            deleteAllTokenServices.deleteAllTokens(user.getId());
            userUpdatechanges = true;
        }
        if (request.getMartialStatus() != null) {
            user.setMartialStatus(request.getMartialStatus());
            userUpdatechanges = true;
        }
        if (request.getGender() != null) {
            user.setGender(request.getGender());
            userUpdatechanges = true;
        }
        if (request.getEmail() != null && !Objects.equals(request.getEmail(), user.getEmail())) {
            user.setEmail(request.getEmail());
            deleteAllTokenServices.deleteAllTokens(user.getId());
            userUpdatechanges = true;
        }
        if (request.getMobile() != null) {
            user.setMobile(request.getMobile());
            userUpdatechanges = true;
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
            userUpdatechanges = true;
        }
        if (request.getBirthday() != null) {
            user.setBirthday(request.getBirthday());
            userUpdatechanges = true;
        }
        if (request.getEmployeeDate() != null) {
            user.setEmployeeDate(request.getEmployeeDate());
            userUpdatechanges = true;
        }
        if (request.getBloodGroup() != null) {
            user.setBloodGroup(request.getBloodGroup());
            userUpdatechanges = true;
        }
        if (request.getRoles() != null) {

            user.setRoles(roleService.findRolesByEnumSet(request.getRoles()));
            deleteAllTokenServices.deleteAllTokens(user.getId());
            userUpdatechanges = true;
        }
        if (request.getFrontPersonalId() != null) {
            user.setFrontPersonalId(imageService.saveImage(request.getFrontPersonalId()));
            userUpdatechanges = true;
        }
        if (request.getBackPersonalId() != null) {
            user.setBackPersonalId(imageService.saveImage(request.getBackPersonalId()));
            userUpdatechanges = true;
        }
        if (request.getFrontMedicalId() != null) {
            user.setFrontMedicalId(imageService.saveImage(request.getFrontMedicalId()));
            userUpdatechanges = true;
        }
        if (request.getBackMedicalId() != null) {
            user.setBackMedicalId(imageService.saveImage(request.getBackMedicalId()));
            userUpdatechanges = true;
        }
        if (request.getImage() != null) {
            user.setImage(imageService.saveImage(request.getImage()));
            userUpdatechanges = true;
        }
        if (!userUpdatechanges) {
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }
    }

}
