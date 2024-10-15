package com.azu.hospital.humanResource.employeeDetails.salaryDetails.service;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.EmployeeDao;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao.EmployeePunishment;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao.EmployeePunishmentDao;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.dao.SalaryDetails;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.dao.SalaryDetailsDao;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.dto.SalaryDetailsDto;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.dto.SalaryDetailsDtoMapper;
import com.azu.hospital.humanResource.employeeDetails.salaryDetails.request.SalaryDetailsRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Service
public class SalaryDetailsService {
  private final SalaryDetailsDao salaryDao;
  private final EmployeeDao employeeDao;
  private final SalaryDetailsDtoMapper dtoMapper;
  private final ExceptionsMessageReturn messageReturn;


  @Autowired
  public SalaryDetailsService(
          @Qualifier("SalaryDetailsJpaDataAccess")
          SalaryDetailsDao salaryDao,
          @Qualifier("EmployeeJpaDataAccess")
          EmployeeDao employeeDao,
          SalaryDetailsDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
  ) {
    this.salaryDao = salaryDao;
    this.employeeDao = employeeDao;
    this.dtoMapper = dtoMapper;
      this.messageReturn = messageReturn;
  }

  public void addSalaryDetails(
          Long employeeId,
          SalaryDetailsRequest request
  ) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    Employee employee = employeeDao
            .getById(employeeId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

    SalaryDetails details = new SalaryDetails(
            request.type(),
            request.amount(),
            request.note()
    );

    details.setEmployee(employee);

    salaryDao.create(details);
  }

  public List<SalaryDetailsDto> getEmployeeSalaryDetails(Long employeeId) {
    return salaryDao.getAllEmployeeSalaryDetails(employeeId)
            .stream()
            .map(dtoMapper::toDto)
            .toList();
  }

  public void updateSalaryDetails(
          Long id,
          SalaryDetailsRequest request
  ) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    SalaryDetails salaryDetails = salaryDao.getById(id)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

    boolean isChanged = false;

    if (
            request.type() != null &&
            !Objects.equals(request.type(), salaryDetails.getType())
    ){
      salaryDetails.setType(request.type());
      isChanged = true;
    }
    if (
            request.amount() != null &&
            !Objects.equals(request.amount(), salaryDetails.getAmount())
    ){
      salaryDetails.setAmount(request.amount());
      isChanged = true;
    }
    if (
            request.note() != null &&
            !Objects.equals(request.note(), salaryDetails.getNote())
    ){
      salaryDetails.setNote(request.note());
      isChanged = true;
    }

    if(!isChanged){
      throw new BadRequestException(
              messages.getString("noChanges")

      );
    }

    salaryDao.update(salaryDetails);
  }

  public void deleteSalaryDetails(Long id){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    SalaryDetails salaryDetails = salaryDao.getById(id)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

    salaryDao.delete(salaryDetails);
  }

}
