package com.azu.hospital.humanResource.employeeDetails.employeePunishment;

import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dto.EmployeePunishmentDto;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.request.EmployeePunishmentRequest;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.services.EmployeePunishmentService;
import com.azu.hospital.utils.return_id.IdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Punishment" , description = "Employee Punishment Routes")
@RestController
@RequestMapping("/api/v1/employee/punishment")
@CrossOrigin

public class EmployeePunishmentController {
  private final EmployeePunishmentService service;

  @Autowired
  public EmployeePunishmentController(EmployeePunishmentService service) {
    this.service = service;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.FOUND)
  public List<EmployeePunishmentDto> getAllPunishments(
          @RequestParam("employee_id") Long employeeId
  ) {
    return service.getAllEmployeePunishment(employeeId);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public IdResponse addPunishment(
          @RequestParam("employee_id") Long employeeId,
          @RequestBody EmployeePunishmentRequest request
  ) {
    return service.addPunishment(employeeId, request);
  }

  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  public void updatePunishment(
          @RequestParam("punishment_id") Long punishmentId,
          @RequestBody EmployeePunishmentRequest request
  ){
    service.updatePunishment(punishmentId, request);
  }

  @PutMapping("/cancel/{punishment_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public void deletePunishment(
          @PathVariable("punishment_id") Long punishmentId
  ){
    service.cancelPunishment(punishmentId);
  }
}
