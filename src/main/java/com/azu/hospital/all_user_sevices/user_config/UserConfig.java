package com.azu.hospital.all_user_sevices.user_config;

import com.azu.hospital.Patients.MedicalHistory.services.MedicalHistoryService;
import com.azu.hospital.Patients.Patient.services.PatientAddService;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorJPADataAccess;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.doctors.services.DoctorAddServices;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseJPADataAccess;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.employee.permanent.dao.PermanentJPADataAccess;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleRepository;
import com.azu.hospital.all_user_sevices.user_dao.UserJPADataAccess;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.bulding.department.services.DepartmentServices;
import com.azu.hospital.bulding.floor.FloorServices;
import com.azu.hospital.bulding.ward.services.WardService;
import com.azu.hospital.bulding.ward.wardBed.service.BedService;
import com.azu.hospital.utils.enums.EnumMartialStatus;
import com.azu.hospital.utils.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserConfig implements ApplicationRunner {

    private final UserJPADataAccess repository;
    private final DoctorJPADataAccess doctorRepository;
    private final NurseJPADataAccess nurseRepository;
    private final PermanentJPADataAccess permanentRepository;

    private final BaseUserDao baseUserDao;
    private final PatientAddService patientServices;
    private final MedicalHistoryService medicalHistoryService;
    private final FloorServices floorServices;
    private final DepartmentServices departmentServices;
    private final WardService wardService;
    private final BedService bedService;


    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
//    private final IAddChatUserService addChatUserService;

    private final DoctorAddServices doctorAddServices;

    @Autowired
    public UserConfig(
            @Qualifier("UserJpa") UserJPADataAccess repository,
            @Qualifier("DoctorJpa") DoctorJPADataAccess doctorRepository,
            @Qualifier("NurseJpa") NurseJPADataAccess nurseRepository,
            @Qualifier("PermanentJpa") PermanentJPADataAccess permanentRepository,
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
            PatientAddService patientServices,
            MedicalHistoryService medicalHistoryService,
            FloorServices floorServices,
            DepartmentServices departmentServices,
            WardService wardService,
            BedService bedService,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
//            IAddChatUserService addChatUserService,
            @Qualifier("DoctorAddService") DoctorAddServices doctorAddServices
    ) {
        this.repository = repository;
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.permanentRepository = permanentRepository;
        this.baseUserDao = baseUserDao;
        this.patientServices = patientServices;
        this.medicalHistoryService = medicalHistoryService;
        this.floorServices = floorServices;
        this.departmentServices = departmentServices;
        this.wardService = wardService;
        this.bedService = bedService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
//        this.addChatUserService = addChatUserService;
        this.doctorAddServices = doctorAddServices;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (baseUserDao.userCount() <= 0) {

            doctorRepository.createNewDoctor(
                    new Doctor.Builder()
                            .withUserName("doctor")
                            .withEmail("doctor@azuapp.com")
                            .withPassword(passwordEncoder.encode("doctor"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("DOCTOR")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            doctorRepository.createNewDoctor(
                    new Doctor.Builder()
                            .withUserName("doctor2")
                            .withEmail("doctor2@azuapp.com")
                            .withPassword(passwordEncoder.encode("doctor2"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("DOCTOR")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );


            doctorRepository.createNewDoctor(
                    new Doctor.Builder()
                            .withUserName("doctor3")
                            .withEmail("doctor3@azuapp.com")
                            .withPassword(passwordEncoder.encode("doctor3"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("DOCTOR")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );


            repository.createNewUser(
                    new User.Builder()
                            .withUserName("pharmacy")
                            .withEmail("pharmacy@azuapp.com")
                            .withPassword(passwordEncoder.encode("pharmacy"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("3234234237564")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("PHARMACISTS")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            repository.createNewUser(
                    new User.Builder()
                            .withUserName("manager")
                            .withEmail("manager@azuapp.com")
                            .withPassword(passwordEncoder.encode("manager"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("HOSPITAL_MANAGER")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            repository.createNewUser(
                    new User.Builder()
                            .withUserName("manager2")
                            .withEmail("manager2@azuapp.com")
                            .withPassword(passwordEncoder.encode("manager2"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("HOSPITAL_MANAGER")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            repository.createNewUser(
                    new User.Builder()
                            .withUserName("manager3")
                            .withEmail("manager3@azuapp.com")
                            .withPassword(passwordEncoder.encode("manager3"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("HOSPITAL_MANAGER")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );



            repository.createNewUser(
                    new User.Builder()
                            .withUserName("Admin")
                            .withEmail("admin@azuapp.com")
                            .withPassword(passwordEncoder.encode("admin"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("ADMINISTRATIVE")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            repository.createNewUser(
                    new User.Builder()
                            .withUserName("Admin2")
                            .withEmail("admin2@azuapp.com")
                            .withPassword(passwordEncoder.encode("admin2"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("ADMINISTRATIVE")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            repository.createNewUser(
                    new User.Builder()
                            .withUserName("Admin3")
                            .withEmail("admin3@azuapp.com")
                            .withPassword(passwordEncoder.encode("admin3"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423411")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("1998-03-03")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("ADMINISTRATIVE")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );



            permanentRepository.createNewPermanent(
                    new Permanent.Builder()
                            .withUserName("permanent")
                            .withEmail("permanent@azuapp.com")
                            .withPassword(passwordEncoder.encode("permanent"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423421121")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("PERMANENT")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );
            nurseRepository.createNewNurse(
                    new Nurse.Builder()
                            .withUserName("nurse")
                            .withEmail("nurse@azuapp.com")
                            .withPassword(passwordEncoder.encode("nurse"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423423")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("NURSES")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            nurseRepository.createNewNurse(
                    new Nurse.Builder()
                            .withUserName("nurse2")
                            .withEmail("nurse2@azuapp.com")
                            .withPassword(passwordEncoder.encode("nurse2"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423423")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("NURSES")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

            nurseRepository.createNewNurse(
                    new Nurse.Builder()
                            .withUserName("nurse3")
                            .withEmail("nurse3@azuapp.com")
                            .withPassword(passwordEncoder.encode("nurse3"))
                            .withEGender(Gender.MALE)
                            .withBirthday("2022-03-03")
                            .withMobile("323423423423")
                            .withEMartialStatus(EnumMartialStatus.MARRIED)
                            .withAddress("Baghdad")
                            .withBloodGroup("bloodGroup")
                            .withEmployeeDate("2020-02-02")
                            .withRoles(roleRepository.findByNames(Set.of("NURSES")))
                            .withSpecialist("ENT_SPECIALIST")
                            .build()
            );

//
//            patientServices.createNewPatient(new CreatePatientRequest(
//                    1L, null, null, null, "dsadsa", JopTypeEnum.GENERAL,
//                    "1998-03-03", "csd", "dfsad@gmail.com", "7709509876",
//                    "7709509876", "asdfasd", "fdsafds", Gender.MALE,
//                    Float.min(10, 15), Float.min(10, 15), CertificationEnum.NO_CERTIFICATION, LiveEnum.Rural,
//                    SocialStateEnum.DIVORCED, "1998-03-03", "1998-03-03",
//                    100, "dsadas", 100, "asdfas", "dasas"
//            ));
//
//            patientServices.createNewPatient(new CreatePatientRequest(
//                    1L, null, null, null, "sdsd", JopTypeEnum.GENERAL,
//                    "1998-03-03", "dsds", "dfsagad@gmail.com", "7709509876",
//                    "7709509876", "Ridha", "fggg", Gender.MALE,
//                    Float.min(10, 15), Float.min(10, 15), CertificationEnum.NO_CERTIFICATION, LiveEnum.Rural,
//                    SocialStateEnum.DIVORCED, "1998-03-03", "1998-03-03",
//                    100, "dsadas", 100, "asdfas", "dasas"
//            ));
//
//            patientServices.createNewPatient(new CreatePatientRequest(
//                    2L, null, null, null, "sdsd", JopTypeEnum.GENERAL,
//                    "1998-03-03", "dsds", "dfsagad@gmail.com", "7709509876",
//                    "7709509876", "Ridha", "fggg", Gender.MALE,
//                    Float.min(10, 15), Float.min(10, 15), CertificationEnum.NO_CERTIFICATION, LiveEnum.Rural,
//                    SocialStateEnum.DIVORCED, "1998-03-03", "1998-03-03",
//                    100, "dsadas", 100, "asdfas", "dasas"
//            ));
//
//            patientServices.createNewPatient(new CreatePatientRequest(
//                    2L, null, null, null, "sdsd", JopTypeEnum.GENERAL,
//                    "1998-03-03", "dsds", "dfsagad@gmail.com", "7709509876",
//                    "7709509876", "Ridha", "fggg", Gender.MALE,
//                    Float.min(10, 15), Float.min(10, 15), CertificationEnum.NO_CERTIFICATION, LiveEnum.Rural,
//                    SocialStateEnum.DIVORCED, "1998-03-03", "1998-03-03",
//                    100, "dsadas", 100, "asdfas", "dasas"
//            ));
//
//            patientServices.createNewPatient(new CreatePatientRequest(
//                    3L, null, null, null, "sdsd", JopTypeEnum.GENERAL,
//                    "1998-03-03", "dsds", "dfsagad@gmail.com", "7709509876",
//                    "7709509876", "Ridha", "fggg", Gender.MALE,
//                    Float.min(10, 15), Float.min(10, 15), CertificationEnum.NO_CERTIFICATION, LiveEnum.Rural,
//                    SocialStateEnum.DIVORCED, "1998-03-03", "1998-03-03",
//                    100, "dsadas", 100, "asdfas", "dasas"
//            ));
//
//            patientServices.createNewPatient(new CreatePatientRequest(
//                    3L, null, null, null, "sdsd", JopTypeEnum.GENERAL,
//                    "1998-03-03", "dsds", "dfsagad@gmail.com", "7709509876",
//                    "7709509876", "Ridha", "fggg", Gender.MALE,
//                    Float.min(10, 15), Float.min(10, 15), CertificationEnum.NO_CERTIFICATION, LiveEnum.Rural,
//                    SocialStateEnum.DIVORCED, "1998-03-03", "1998-03-03",
//                    100, "dsadas", 100, "asdfas", "dasas"
//            ));
//
//            floorServices.createNewFloor(new FloorBaseRequest(1, "F1"));
//            departmentServices.createNewDep(1L, new DepartmentBaseRequest("Main", 1L, 2L));

        }
    }
}
