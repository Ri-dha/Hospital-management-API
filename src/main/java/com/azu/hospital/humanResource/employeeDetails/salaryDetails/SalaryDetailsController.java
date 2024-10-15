package com.azu.hospital.humanResource.employeeDetails.salaryDetails;

import com.azu.hospital.humanResource.employeeDetails.salaryDetails.dto.SalaryDetailsDto;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.request.SalaryDetailsRequest;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.service.SalaryDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Salary" , description = "Salary Routes")
@RestController
@RequestMapping("/api/v1/employee/salary")
@CrossOrigin

public class SalaryDetailsController {
  private final SalaryDetailsService service;

  @Autowired
  public SalaryDetailsController(SalaryDetailsService service) {
    this.service = service;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<SalaryDetailsDto> getAllSalaryDetails(
          @RequestParam("employee_id") Long employeeId
  ){
    return service.getEmployeeSalaryDetails(employeeId);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
    public void addSalaryDetails(
          @RequestParam("employee_id") Long employeeId,
          @RequestBody SalaryDetailsRequest request
  ){
    service.addSalaryDetails(employeeId, request);
  }

  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  public void updateSalaryDetails(
          @RequestParam("salary_details_id") Long salaryDetailsId,
          @RequestBody SalaryDetailsRequest request
  ){
    service.updateSalaryDetails(salaryDetailsId, request);
  }

  @DeleteMapping("/{salary_details_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public void deleteSalaryDetails(
          @PathVariable("salary_details_id") Long salaryDetailsId
  ){
    service.deleteSalaryDetails(salaryDetailsId);
  }
}
