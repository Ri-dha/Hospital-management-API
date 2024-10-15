package com.azu.hospital.humanResource.employeeDetails.employee.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.EmployeeDao;
import com.azu.hospital.humanResource.employeeDetails.employee.dto.EmployeeDto;
import com.azu.hospital.humanResource.employeeDetails.employee.dto.EmployeeDtoMapper;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.EmployeeStatusEnum;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
  private final EmployeeDao employeeDao;
  private final EmployeeDtoMapper dtoMapper;

  @Autowired
  public EmployeeService(
          @Qualifier("EmployeeJpaDataAccess")
          EmployeeDao employeeDao,
          EmployeeDtoMapper dtoMapper
  ) {
    this.employeeDao = employeeDao;
    this.dtoMapper = dtoMapper;
  }

  public List<EmployeeDto> getRandomEmployee() {
    return employeeDao.getRandomEmployee()
            .stream()
            .map(dtoMapper::toDto)
            .toList();
  }

  @Transactional
  public List<EmployeeDto> getALLEmployee(
          EmployeeStatusEnum status,
          int page
  ) {
    Pageable pageable = PageRequest.of(page, 10);
    Page<Employee> employees;

    if (status == null) {
      return employeeDao.getAllStaff(pageable)
              .stream()
              .map(dtoMapper::toDto)
              .toList();
    }

    switch (status) {
      case BONUS -> {
        employees = employeeDao.getEmployeeWithBonus(pageable);
      }
      case RAISE -> {
        employees = employeeDao.getEmployeeWithRaise(pageable);
      }
      case PAYCAT -> {
        employees = employeeDao.getEmployeeWithPaycat(pageable);
      }
      default -> employees = employeeDao.getAllStaff(pageable);
    }
    return employees
            .stream()
            .map(dtoMapper::toDto)
            .toList();
  }
}
