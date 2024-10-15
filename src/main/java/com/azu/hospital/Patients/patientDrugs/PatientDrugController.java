package com.azu.hospital.Patients.patientDrugs;

import com.azu.hospital.Patients.patientDrugs.dto.PatientDrugDto;
import com.azu.hospital.Patients.patientDrugs.request.PatientDrugRequest;
import com.azu.hospital.Patients.patientDrugs.request.PatientDrugRequestUpdate;
import com.azu.hospital.Patients.patientDrugs.services.PatientDrugService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "Patient Drugs" , description = "Patient Drugs Routes")
@RestController
@RequestMapping("/api/v1/charts/doctor-tour/drugs")
@CrossOrigin
public class PatientDrugController {
  private final PatientDrugService service;

  @Autowired
  public PatientDrugController(PatientDrugService service) {
    this.service = service;
  }

  @GetMapping
//  @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
//          ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
//          "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
//          "  , 'PHARMACISTS')")
  public List<PatientDrugDto> getAllDrugs(
          @RequestParam("patient_id") Long patientId
  ) {
    return service.getDrugs(patientId);
  }

  @PostMapping
//  @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES' " +
//          ",'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', " +
//          "'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER' " +
//          " )")
  public ResponseEntity<Void> addNewDrugs(
          @RequestParam("patient_id") Long patientId,
          @RequestBody List<PatientDrugRequest> request
  ) {
    service.addNewDrug(patientId, request);
    return ResponseEntity.ok().build();
  }

  @PutMapping
//  @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES' " +
//          ",'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', " +
//          "'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER' " +
//          " )")
  public ResponseEntity<?> updateDrugById(
          @RequestParam("drug_id") Long drugId,
          @RequestBody List<PatientDrugRequestUpdate> request
  ) {
    return ResponseEntity.ok(service.updateExistsDrug(drugId, request));
  }
}
