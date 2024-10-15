package com.azu.hospital.humanResource.employeeDetails.employeePunishment.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employee.dao.EmployeeDao;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.PunishmentStatusEnum;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao.EmployeePunishment;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao.EmployeePunishmentDao;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dto.EmployeePunishmentDto;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.dto.EmployeePunishmentDtoMapper;
import com.azu.hospital.humanResource.employeeDetails.employeePunishment.request.EmployeePunishmentRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Service
public class EmployeePunishmentService {
  private final EmployeePunishmentDao punishmentDao;
  private final EmployeeDao employeeDao;
  private final EmployeePunishmentDtoMapper dtoMapper;
  private final ExceptionsMessageReturn messageReturn;


  @Autowired
  public EmployeePunishmentService(@Qualifier("EmployeePunishmentJpaDataAccess") EmployeePunishmentDao punishmentDao, @Qualifier("EmployeeJpaDataAccess") EmployeeDao employeeDao, EmployeePunishmentDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn) {
    this.punishmentDao = punishmentDao;
    this.employeeDao = employeeDao;
    this.dtoMapper = dtoMapper;
      this.messageReturn = messageReturn;
  }

  public List<EmployeePunishmentDto> getAllEmployeePunishment(Long employeeId) {
    return punishmentDao.
            getPunishmentByEmployeeId(employeeId)
            .stream()
            .map(dtoMapper::toDto)
            .toList();
  }

  public IdResponse addPunishment(Long employeeId, EmployeePunishmentRequest request) {

    Employee employee = employeeDao.getById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

    EmployeePunishment punishment = new EmployeePunishment(
            request.punishmentType(),
            request.punishmentNote()
    );

    punishment.setEmployee(employee);

    EmployeePunishment saved = punishmentDao.create(punishment);

    return new DtoForReturnIdOnly(saved.getPunishmentId());
  }

  public void updatePunishment(Long punishmentId, EmployeePunishmentRequest request) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    EmployeePunishment punishment = punishmentDao
            .getById(punishmentId)
            .orElseThrow(() -> new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            ));

    boolean changed = false;

    if (
            request.punishmentNote() != null &&
                    !request.punishmentNote().isBlank() &&
                    !Objects.equals(punishment.getPunishmentNote(), request.punishmentNote())
    ) {
      punishment.setPunishmentNote(request.punishmentNote());
      changed = true;
    }
    if (
            request.punishmentType() != null &&
                    !Objects.equals(punishment.getPunishmentType(), request.punishmentType())
    ) {
      punishment.setPunishmentType(request.punishmentType());
      changed = true;
    }

    if (!changed) {
      throw new ResourceNotFoundException(
              messages.getString("noChanges")
      );
    }
    punishmentDao.update(punishment);
  }

  public void cancelPunishment(Long punishmentId) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
    EmployeePunishment punishment = punishmentDao
            .getById(punishmentId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );
    punishment.setStatus(PunishmentStatusEnum.CANCELED);
    punishmentDao.update(punishment);
  }
}
