package com.azu.hospital.lab_collection.external.dto;

import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.external.ext_tests_request.dto.ExtTestRequestDtoMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExternalLabWithResultDtoMapper implements Function<ExternalLabTest,ExternalLabWithResultDto> {
  private final ExtTestRequestDtoMapper extTestRequestDtoMapper;

  public ExternalLabWithResultDtoMapper(ExtTestRequestDtoMapper extTestRequestDtoMapper) {
    this.extTestRequestDtoMapper = extTestRequestDtoMapper;
  }

  @Override
  public ExternalLabWithResultDto apply(ExternalLabTest externalLabTest) {
    return new ExternalLabWithResultDto(
            externalLabTest.getId(),
            externalLabTest.getPatientName(),
            externalLabTest.getDoctorName(),
            externalLabTest.getGender(),
            externalLabTest.getAge(),
            externalLabTest.getAdmissionDate(),
            externalLabTest.getTestRequests()
                    .stream()
                    .filter(extTestRequest -> !extTestRequest.getExtTestRequest().isEmpty())
                    .map(extTestRequestDtoMapper::toDto)
                    .toList(),
            externalLabTest.getCreatedBy(),
            externalLabTest.getLastModifiedBy()
    );
  }
}
