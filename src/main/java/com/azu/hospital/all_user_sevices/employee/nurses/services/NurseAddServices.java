package com.azu.hospital.all_user_sevices.employee.nurses.services;


import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUserService;

import com.azu.hospital.exceptions.validators.NurseRoleValidator;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.EmployeeDao;
import com.azu.hospital.payload.request.ImageUpload;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("NurseAddService")
public class NurseAddServices {

    private final NurseDao nurseDao;
    private final EmployeeDao employeeDao;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final AppUserService appUserService;
    private final NurseRoleValidator nurseRoleValidator;
//    private final IAddChatUserService addChatUserService;

    private final RoleService roleService;



    @Autowired
    public NurseAddServices(
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("EmployeeJpaDataAccess") EmployeeDao employeeDao,
            @Qualifier("PassEncoder") PasswordEncoder passwordEncoder,
            @Qualifier("imageService") ImageService imageService,
            @Qualifier("AppUserService") AppUserService appUserService,
            NurseRoleValidator nurseRoleValidator,
//            @Qualifier("AddChatUserService") IAddChatUserService addChatUserService,
            @Qualifier("roleService") RoleService roleService) {
        this.nurseDao = nurseDao;
        this.employeeDao = employeeDao;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
        this.appUserService = appUserService;
        this.nurseRoleValidator = nurseRoleValidator;
//        this.addChatUserService = addChatUserService;

        this.roleService = roleService;

    }

   @Transactional
    public void createNewNurse(AllUserRegistrationRequest request) throws IOException {

        nurseRoleValidator.validateRoles(request.getRoles());

        appUserService.checkIfEmailExists(request.getEmail());


         ImageUpload ImageRecord = getImageUpload(request);

        Nurse nurse = createNewNurseInstanceMethodPrivate(request, ImageRecord);

        Employee employee = new Employee();

        Nurse newNurse = nurseDao.createNewNurse(nurse);

        employee.setNurse(newNurse);

        employeeDao.createEmployee(employee);


//       ChatUserRequest chatUser = new ChatUserRequest(
//               newNurse.getId(),
//               newNurse.getEmail(),
//               newNurse.getUsernameSpecial(),
//               newNurse.getImage(),
//               newNurse.getMobile()
//       );
//
//       addChatUserService.createUser(chatUser);
    }

    @NotNull
    private Nurse createNewNurseInstanceMethodPrivate(AllUserRegistrationRequest request, ImageUpload ImageRecord) {

        return new Nurse
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
