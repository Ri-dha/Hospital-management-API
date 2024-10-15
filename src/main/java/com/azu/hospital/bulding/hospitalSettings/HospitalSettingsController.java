package com.azu.hospital.bulding.hospitalSettings;

import com.azu.hospital.bulding.hospitalSettings.dto.HospitalSettingsDto;
import com.azu.hospital.bulding.hospitalSettings.request.HospitalSettingsRequest;
import com.azu.hospital.bulding.hospitalSettings.service.HospitalSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospital-settings")
@CrossOrigin

public class HospitalSettingsController {

  private final HospitalSettingsService service;

  @Autowired
  public HospitalSettingsController(HospitalSettingsService service) {
    this.service = service;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public HospitalSettingsDto getHospitalSettings() {
    return service.getHospitalSettings();
  }

  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  public HospitalSettingsDto updateHospitalSettings(
          @RequestBody
          HospitalSettingsRequest request
  ) {
    return service.updateHospitalSettings(request);
  }
}
