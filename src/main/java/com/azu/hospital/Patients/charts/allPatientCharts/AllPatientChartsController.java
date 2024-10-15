package com.azu.hospital.Patients.charts.allPatientCharts;

import com.azu.hospital.Patients.charts.allPatientCharts.dto.AllPatientChartsDto;
import com.azu.hospital.Patients.charts.allPatientCharts.services.AllPatientChartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/charts")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES'" +
        ",'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF'," +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class AllPatientChartsController {

  private final AllPatientChartServices services;

  @Autowired
  public AllPatientChartsController(
          AllPatientChartServices services
  ) {
    this.services = services;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public AllPatientChartsDto getExistsChart(
          @RequestParam("patient_id") Long patientId
  ){
    return services.getExistsCharts(patientId);
  }

}
