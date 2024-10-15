//package com.azu.hospital.all_user_sevices.employee.nurses.config;
//
//import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
//import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseRepository;
//import com.azu.hospital.utils.enums.EnumMartialStatus;
//import com.azu.hospital.utils.enums.EnumRole;
//import com.azu.hospital.utils.enums.Gender;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Configuration;
//
//import java.math.BigDecimal;
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//public class NurseConfig implements ApplicationRunner {
//
//  private final NurseRepository repository;
//
//  @Autowired
//  public NurseConfig(NurseRepository repository) {
//    this.repository = repository;
//  }
//
//  @Override
//  public void run(ApplicationArguments args) throws Exception {
//    if(repository.count() == 0){
//      Set<EnumRole> roles = new HashSet<>();
//
//      roles.add(EnumRole.ADMINISTRATIVE);
//      roles.add(EnumRole.HOSPITAL_MANAGER);
//      roles.add(EnumRole.DOCTOR);
//      roles.add(EnumRole.PERMANENT);
//
//      repository.save(
//              new Nurse.Builder()
//                      .withUserName("haider")
//                      .withEmail("haider@gmail.com")
//                      .withPassword("password")
//                      .withEGender(Gender.MALE)
//                      .withBirthday("2022-03-03")
//                      .withMobile("3234234234")
//                      .withEMartialStatus(EnumMartialStatus.MARRIED)
//                      .withAddress("Baghdad")
//                      .withBloodGroup("bloodGroup")
//                      .withMonthSalary(new BigDecimal(3333))
//                      .withEmployeeDate("2020-02-02")
//                      .withRoles(roles)
//                      .withSpecialist("ENT_SPECIALIST")
//                      .build()
//      );
//    }
//  }
//}
