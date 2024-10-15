package com.azu.hospital.humanResource.employeeDetails.employee;

import com.azu.hospital.humanResource.employeeDetails.employee.dto.EmployeeDto;
import com.azu.hospital.humanResource.employeeDetails.employee.services.EmployeeService;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.EmployeeStatusEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee" , description = "Employee Routes")
@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin

public class EmployeeController {
  private final EmployeeService service;

  @Autowired
  public EmployeeController(EmployeeService service) {
    this.service = service;
  }

  @GetMapping("/main")
  @ResponseStatus(code = HttpStatus.OK)
  public List <EmployeeDto> getRandomEmployee() {
    return service.getRandomEmployee();
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<EmployeeDto> getAllEmployee(
          @RequestParam(required = false, value = "filter") EmployeeStatusEnum status,
          @RequestParam(required = false, value = "page", defaultValue = "0") int page
  ) {
    return service.getALLEmployee(status, page);
  }
}
