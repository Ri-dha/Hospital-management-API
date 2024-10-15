package com.azu.hospital.Patients.charts.vitalSignChart;

import com.azu.hospital.Patients.charts.vitalSignChart.dto.VitalSignDto;
import com.azu.hospital.Patients.charts.vitalSignChart.request.VitalSignRequest;
import com.azu.hospital.Patients.charts.vitalSignChart.services.VitalSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/vital-sign")
@CrossOrigin
//@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES'" +
//        ", 'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', " +
//        "'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER' " +
//        " )")
public class VitalSignController {
  private final VitalSignService vitalSignService;

  @Autowired
  public VitalSignController(VitalSignService vitalSignService) {
    this.vitalSignService = vitalSignService;
  }

  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public VitalSignDto getVitalSignById(
          @PathVariable("id") Long id
  ) {
    return vitalSignService.getVitalSignById(id);
  }


  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<VitalSignDto> getVitalSignByPatientId(
          @RequestParam("patient_id")
          Long patientId
  ) {
    return vitalSignService.getAllVitalSignByPatientId(patientId);
  }

  @PostMapping
  public ResponseEntity<?> createVitalSign(
          @RequestParam("patient_id") Long patientId,
          @RequestBody VitalSignRequest request
  ) {
    return new ResponseEntity<>(
            vitalSignService.createNewVitalSign(patientId, request),
            HttpStatus.CREATED
    );
  }

  @PutMapping
  public ResponseEntity<?> updateVitalSign(
          @RequestParam("id") Long vitalSignId,
          @RequestBody VitalSignRequest request
  ) {
    return new ResponseEntity<>(
            vitalSignService.updateExistsVitalSign(vitalSignId, request),
            HttpStatus.CREATED
    );
  }

}
