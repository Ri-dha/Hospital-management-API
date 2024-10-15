package com.azu.hospital.Patients.patientBill.controller;

import com.azu.hospital.Patients.patientBill.request.DrugItemUpdateRequest;
import com.azu.hospital.Patients.patientBill.request.LabTestUpdateRequest;
import com.azu.hospital.Patients.patientBill.request.OperationUpdateRequest;
import com.azu.hospital.Patients.patientBill.service.PatientBillServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/patient-bill")
@CrossOrigin
public class PatientBillControllers {

  private final PatientBillServices service;

  @Autowired
  public PatientBillControllers(PatientBillServices service) {
    this.service = service;
  }



  @PutMapping("/operation")
  public ResponseEntity<Void> updateOperationBill(
          @RequestParam(name = "id") Long id,
          @RequestBody OperationUpdateRequest request
  ){
    service.updateOperationBill(id,request);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/drug")
  public ResponseEntity<Void> updateDrugItem(
          @RequestParam(name = "id") Long id,
          @RequestBody DrugItemUpdateRequest request
          ){
    service.updateDrugItem(id,request);
    return ResponseEntity.ok().build();
  }


  @PutMapping("/test")
  public ResponseEntity<Void> updateTest(
          @RequestParam(name = "id") Long id,
          @RequestBody LabTestUpdateRequest request
          ){
    service.updateTest(id,request);
    return ResponseEntity.ok().build();
  }


}
