package com.azu.hospital.all_user_sevices.employee.permanent.services;


import com.azu.hospital.all_user_sevices.employee.permanent.dao.PermanentDao;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUserService;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.EmployeeDao;
import com.azu.hospital.payload.request.ImageUpload;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.image.ImageService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("PermanentAddService")
public class PermanentAddServices {


    private final PermanentDao permanentDao;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final AppUserService appUserService;
    private final EmployeeDao employeeDao;
    private final RoleService roleService;

//    private final IAddChatUserService addChatUserService;
    private final ExceptionsMessageReturn messageReturn;




    @Autowired
    public PermanentAddServices(
            @Qualifier("PermanentJpa") PermanentDao permanentDao,
            @Qualifier("PassEncoder")
            PasswordEncoder passwordEncoder,
            ImageService imageService, AppUserService appUserService,
            @Qualifier("EmployeeJpaDataAccess")EmployeeDao employeeDao, RoleService roleService,
//            @Qualifier("AddChatUserService") IAddChatUserService addChatUserService,
            ExceptionsMessageReturn messageReturn) {
        this.permanentDao = permanentDao;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
        this.appUserService = appUserService;
        this.employeeDao = employeeDao;
        this.roleService = roleService;
//        this.addChatUserService = addChatUserService;
        this.messageReturn = messageReturn;
    }


    public void createNewPermanent(AllUserRegistrationRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        if (request.getRoles().contains(EnumRole.DOCTOR) || request.getRoles().contains(EnumRole.NURSES)){
            throw new BadRequestException(
                    messages.getString("existingRoles"));
        }

        if (!request.getRoles().contains(EnumRole.PERMANENT)){
            throw new BadRequestException(
                    messages.getString("notExistingRoles"));
        }

        appUserService.checkIfEmailExists(request.getEmail());


        ImageUpload ImageRecord = getImageUpload(request);

        Permanent permanent = createNewPermanentInstanceMethodPrivate(request, ImageRecord);

        Employee employee = new Employee();

        Permanent newPermanent = permanentDao.createNewPermanent(permanent);

        employee.setPermanent(newPermanent);

        employeeDao.createEmployee(employee);


//
//        ChatUserRequest chatUser = new ChatUserRequest(
//                newPermanent.getId(),
//                newPermanent.getEmail(),
//                newPermanent.getUsernameSpecial(),
//                newPermanent.getImage(),
//                newPermanent.getMobile()
//        );
//
//        addChatUserService.createUser(chatUser);

    }


    @NotNull
    private Permanent createNewPermanentInstanceMethodPrivate(AllUserRegistrationRequest request, ImageUpload ImageRecord) {

        return new Permanent
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
