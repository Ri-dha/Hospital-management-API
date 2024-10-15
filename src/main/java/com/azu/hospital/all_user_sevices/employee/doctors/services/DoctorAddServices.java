package com.azu.hospital.all_user_sevices.employee.doctors.services;


import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.regestrations_request.AllUserRegistrationRequest;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleService;
import com.azu.hospital.all_user_sevices.user_utils.lookup.AppUserService;

import com.azu.hospital.exceptions.validators.DoctorRoleValidator;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.EmployeeDao;
import com.azu.hospital.payload.request.ImageUpload;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service("DoctorAddService")
public class DoctorAddServices {

    private final DoctorDao doctorDao;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeDao employeeDao;
    private final ImageService imageService;
    private final AppUserService appUserService;
    private final RoleService roleService;
//    private final IAddChatUserService addChatUserService;
    private final DoctorRoleValidator roleValidator;
    private final ExceptionsMessageReturn messageReturn;




    @Autowired
    public DoctorAddServices(
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            @Qualifier("PassEncoder")
            PasswordEncoder passwordEncoder,
            EmployeeDao employeeDao, ImageService imageService,
            AppUserService appUserService,
            RoleService roleService,
//            @Qualifier("AddChatUserService") IAddChatUserService addChatUserService,
            DoctorRoleValidator roleValidator, ExceptionsMessageReturn messageReturn
    ) {
        this.doctorDao = doctorDao;
        this.passwordEncoder = passwordEncoder;
        this.employeeDao = employeeDao;
        this.imageService = imageService;
        this.appUserService = appUserService;
        this.roleService = roleService;
//        this.addChatUserService = addChatUserService;
        this.roleValidator = roleValidator;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void createNewDoctor(AllUserRegistrationRequest request) throws IOException {

        roleValidator.validateRoles(request.getRoles());

        appUserService.checkIfEmailExists(request.getEmail());

        ImageUpload ImageRecord = getImageUpload(request);

        Doctor doctor = createNewDoctorInstanceMethodPrivate(request, ImageRecord);

        Employee employee = new Employee();

        Doctor newDoctor = doctorDao.createNewDoctor(doctor);

        employee.setDoctor(newDoctor);

        employeeDao.createEmployee(employee);

//        ChatUserRequest chatUser = new ChatUserRequest(
//                newDoctor.getId(),
//                newDoctor.getEmail(),
//                newDoctor.getUsernameSpecial(),
//                newDoctor.getImage(),
//                newDoctor.getMobile()
//        );
//
//        addChatUserService.createUser(chatUser);

    }

    @NotNull
    private Doctor createNewDoctorInstanceMethodPrivate(AllUserRegistrationRequest request, ImageUpload ImageRecord) {

        Set<Role> role = roleService.findRolesByEnumSet(request.getRoles());
        return new Doctor
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
                .withRoles(role)
                .withSpecialist(request.getSpecialty())
                .withSubSpecialist(request.getSubSpecialty())
//                .withAppointmentNumber(request.getAppointmentNumber())
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
