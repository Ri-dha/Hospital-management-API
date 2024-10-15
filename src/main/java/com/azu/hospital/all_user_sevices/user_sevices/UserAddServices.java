package com.azu.hospital.all_user_sevices.user_sevices;

import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;
import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUserService;
import com.azu.hospital.exceptions.validators.UserRoleValidator;
import com.azu.hospital.payload.request.ImageUpload;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("UserAddService")
public class UserAddServices {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final AppUserService appUserService;
    private final RoleService roleService;

//    private final IAddChatUserService addChatUserService;

    private final UserRoleValidator userRoleValidator;


    @Autowired
    public UserAddServices(
            @Qualifier("UserJpa")UserDao userDao,
            @Qualifier("PassEncoder") PasswordEncoder passwordEncoder,
            ImageService imageService,
            AppUserService appUserService,
            RoleService roleService,
//            @Qualifier("AddChatUserService") IAddChatUserService iAddChatUserService,
            @Qualifier("UserRole") UserRoleValidator userRoleValidator) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
        this.appUserService = appUserService;
        this.roleService = roleService;
//        this.addChatUserService = iAddChatUserService;
        this.userRoleValidator = userRoleValidator;
    }

    @Transactional
    public void createNewUser(AllUserRegistrationRequest request) throws IOException {


        userRoleValidator.validateRoles(request.getRoles());
        appUserService.checkIfEmailExists(request.getEmail());

        ImageUpload ImageRecord = getImageUpload(request);

        User user = createNewUserInstanceMethodPrivate(request, ImageRecord);

       userDao.createNewUser(user);


//
//        ChatUserRequest chatUser = new ChatUserRequest(
//                user.getId(),
//                user.getEmail(),
//                user.getUsernameSpecial(),
//                user.getImage(),
//                user.getMobile()
//        );
//        addChatUserService.createUser(chatUser);

    }


    @NotNull
    private User createNewUserInstanceMethodPrivate(AllUserRegistrationRequest request, ImageUpload ImageRecord) {

        return new User
                .Builder()
                .withUserName(request.getUsername())
                .withEmail(request.getEmail())
                .withPassword(passwordEncoder.encode(request.getPassword()))
                .withEGender(request.getGender())
                .withBirthday(request.getBirthday())
                .withMobile(request.getMobile())
                .withEMartialStatus(request.getMartialStatus())
                .withAddress(request.getAddress())
                .withBloodGroup(request.getBloodGroup())
                .withEmployeeDate(request.getEmployeeDate())
                .withImage(ImageRecord.image())
                .withFrontPersonalId(ImageRecord.frontPersonalId())
                .withBackPersonalId(ImageRecord.backPersonalId())
                .withFrontMedicalId(ImageRecord.frontMedicalId())
                .withBackMedicalId(ImageRecord.backMedicalId())
                .withRoles(roleService.findRolesByEnumSet(request.getRoles()))
                .withSpecialist(request.getSpecialty())
                .build();
    }




    public ImageUpload getImageUpload(AllUserRegistrationRequest request) throws IOException {
        return getImageUpload(imageService, request.getImage(), request.getFrontMedicalId(), request.getFrontPersonalId(), request.getBackMedicalId(), request.getBackPersonalId());
    }

    @NotNull
    public static ImageUpload getImageUpload(ImageService imageService, MultipartFile image2, MultipartFile frontMedicalId2, MultipartFile frontPersonalId2, MultipartFile backMedicalId2, MultipartFile backPersonalId2) throws IOException {
        String image = imageService.saveImage(image2);
        String frontMedicalId = imageService.saveImage(frontMedicalId2);
        String frontPersonalId = imageService.saveImage(frontPersonalId2);
        String backMedicalId = imageService.saveImage(backMedicalId2);
        String backPersonalId = imageService.saveImage(backPersonalId2);
        return new ImageUpload(image, frontMedicalId, frontPersonalId, backMedicalId, backPersonalId);
    }


}
