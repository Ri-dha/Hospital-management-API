//package com.azu.hospital.Patients.config;
//
//import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
//import com.azu.hospital.Patients.PatentEnum.CertificationEnum;
//import com.azu.hospital.Patients.Patient.Entity.Patient;
//import com.azu.hospital.Patients.Patient.dao.PatientRepository;
//import com.azu.hospital.Patients.Patient.request.*;
//import com.azu.hospital.bulding.ward.entity.Ward;
//import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
//import com.azu.hospital.utils.enums.Gender;
//import com.azu.hospital.utils.enums.patient.JopTypeEnum;
//import com.azu.hospital.utils.enums.patient.LiveEnum;
//import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
//import com.azu.hospital.utils.enums.patient.SocialStateEnum;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class PatientConfig implements ApplicationRunner {
//
//  private final PatientRepository repository;
//
//  @Autowired
//  public PatientConfig(PatientRepository repository) {
//    this.repository = repository;
//  }
//
//  @Override
//  public void run(ApplicationArguments args) throws Exception {
//    if (repository.count() == 0) {
//      Patient patient = new Patient.Builder()
//              .withJobInfo(new JobInfo("job", JopTypeEnum.PRIVATE))
//              .withPatientAddress(
//                      new PatientAddress(
//
//                              "AMARA",
//                              "AMARA"
//                      )
//              )
//              .withPatientContact(
//                      new PatientContact(
//                              "email@gmail.com",
//                              "032323232",
//                              "dsfasdfa"
//                      ))
//              .withPatientData(
//                      new PatientData(
//                              "name",
//                              "mom",
//                              Gender.MALE, 9.2F,
//                              189.300000000F, CertificationEnum.DIPLOMA,
//                              LiveEnum.Rural,
//                              SocialStateEnum.MARRIED
//                      )
//              )
//              .withPatientDate(
//                      new PatientDate(
//                              "2017-02-12",
//                              "2017-02-12",
//                              22
//                      )
//              )
//              .withPatientMedicalInfo(
//                      new PatientMedicalInfo(
//                              "dfghjkl",
//                              45, "123456789",
//                              "hdsgfhdsgfjhdsgfjhsd"
//
//                      )
//              )
//              .build();
//      patient.setWard(new Ward("Ward Name"));
//      patient.setBed(new Bed("bed number"));
//      MedicalHistory medicalHistory = new MedicalHistory(
//              "dx",
//              "chiefComplaint",
//              "chiefComplaint",
//              "familyHistory",
//              "drugHistoryAllergy",
//              "historyPresentIllness",
//              PatientTriageEnum.Emergency
//      );
//      medicalHistory.setPatient(patient);
//      patient.setMedicalHistory(List.of(medicalHistory));
//      repository.save(patient);
//    }
//  }
//}