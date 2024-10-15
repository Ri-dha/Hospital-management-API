package com.azu.hospital.lab_collection.external.dao;

import com.azu.hospital.lab_collection.external.dto.ExternalLabDto;
import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("externalLabRepo")
public class ExternalLabDataJpa implements ExternalLabDao {

    private final ExternalLabRepository externalLabRepository;

    @Autowired
    public ExternalLabDataJpa(ExternalLabRepository externalLabRepository) {
        this.externalLabRepository = externalLabRepository;
    }


    @Override
    public List<DateTimeTest> getDateTimeTest(LocalDate date) {
        return externalLabRepository.getDateTimeTestRaw(date);
    }

    @Override
    public Page<ExternalLabTest> getAllExternalTest(List<LabRequestStatusEnum> states, Pageable pageable) {
        return externalLabRepository.getExternalLabTest(states , pageable);
    }

    @Override
    public Page<ExternalLabTest> getAllExternalTestNotArchived(Pageable pageable) {
        return externalLabRepository.getAllByStateIsNotOrderByAdmissionDateDesc(LabRequestStatusEnum.Archived, pageable);
    }

    @Override
    public Long countAll() {
        return externalLabRepository.count();
    }

    @Override
    public Page<ExternalLabTest> getAllExternalTestArchived(Pageable pageable) {
        return externalLabRepository.getAllByStateIsOrderByAdmissionDateDesc(Arrays.asList(LabRequestStatusEnum.Archived,LabRequestStatusEnum.Cancel), pageable);
    }


    @Override
    public ExternalLabTest createNewExternalTest(ExternalLabTest externalLabTest) {
        return externalLabRepository.save(externalLabTest);
    }

    @Override
    public Optional<ExternalLabTest> findById(Long id) {
        return externalLabRepository.findById(id);
    }

    @Override
    public void updateExternalTest(ExternalLabTest externalLabTest) {
        externalLabRepository.save(externalLabTest);
    }

    @Override
    public Optional<ExternalLabTest> getExternalLabByRequestId(Long labId) {
        return Optional.empty();
    }


}
