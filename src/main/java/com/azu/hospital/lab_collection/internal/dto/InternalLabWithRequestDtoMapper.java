package com.azu.hospital.lab_collection.internal.dto;

import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dto.IntTestRequestDtoMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InternalLabWithRequestDtoMapper implements Function<InternalLabTest,InternalLabWithRequestDto> {
  private final IntTestRequestDtoMapper intTestRequestDtoMapper;

  public InternalLabWithRequestDtoMapper(IntTestRequestDtoMapper intTestRequestDtoMapper) {
    this.intTestRequestDtoMapper = intTestRequestDtoMapper;
  }


  @Override
  public InternalLabWithRequestDto apply(InternalLabTest internalLabTest) {
    return new InternalLabWithRequestDto(
            internalLabTest.getId(),
            internalLabTest.getPatient().getId(),
            internalLabTest.getPatient().getDoctorSpecials().getDoctor().getUsername(),
            internalLabTest.getPatient().getPatientData().getFullName(),
            internalLabTest.getPatient().getPatientData().getGender().name(),
            internalLabTest.getPatient().getPatientDate().getAge(),
            internalLabTest.getPatient().getBed().getBedNumber(),
            internalLabTest.getPatient().getWard().getName(),
            internalLabTest.getCreatedAt().toString(),
            internalLabTest.getTestRequests().stream()
                    .filter(intTestRequest -> !intTestRequest.getIntTestResults().isEmpty())
                    .map(intTestRequestDtoMapper::toDto)
                    .toList(),
            internalLabTest.getCreatedBy(),
            internalLabTest.getLastModifiedBy()
            );


  }
}
