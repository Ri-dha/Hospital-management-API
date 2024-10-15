package com.azu.hospital.lab_collection.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.int_tests_request.dto.InternalLabTestsBillsDto;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("internalLabRepo")
public class InternalLabDataJpa implements InternalLabDao {

    private final InternalLabRepository internalLabRepository;

    @Autowired
    public InternalLabDataJpa(InternalLabRepository internalLabRepository) {
        this.internalLabRepository = internalLabRepository;
    }

    @Override
    public Long countAllInternalLab() {
        return internalLabRepository.countAllBy();
    }

    @Override
    public List<DateTimeTest> getDateTimeTest(LocalDate date) {
        return internalLabRepository.getDateTimeTestRaw(date);
    }


    @Override
    public InternalLabTest createNewInternalTest(InternalLabTest internalLabTest) {
        return internalLabRepository.save(internalLabTest);
    }

    @Override
    public Long countAll() {
        return internalLabRepository.count();
    }

    @Override
    public Optional<InternalLabTest> findById(Long id) {
        return internalLabRepository.findById(id);
    }

    @Override
    public void updateInternalTest(InternalLabTest internalLabTest) {
        internalLabRepository.save(internalLabTest);
    }

    @Override
    public Page<InternalLabTest> getAllConsultantTest(Pageable pageable) {
        return internalLabRepository.getAllByLabTestDestination(TestDestination.Consultant, pageable);
    }

    @Override
    public List<InternalLabTest> getAllByConsultantPatientId(Long patientId) {
        return internalLabRepository.getAllByLabTestDestinationAndConsultantPatientId(TestDestination.Consultant,
                patientId);
    }

    @Override
    public Page<InternalLabTest> getAllByState(List<InternalLabRequestStatusEnum> states, Pageable pageable) {
        return internalLabRepository.getAllByStates(states, pageable);
    }

    @Override
    public Page<InternalLabTest> getAllByPatientId(Long patientId, Pageable pageable) {
        return internalLabRepository.getAllByPatientId(patientId, pageable);
    }

    public Optional<InternalLabTest> getInternalLabByRequestId(Long labId) {
        return internalLabRepository.findByRequestId(labId);
    }

    @Override
    public List<BillsDtoSum<String>> getAllInternalLabWithSum(Long patientId) {
        return internalLabRepository.getAllWithSum(patientId);
    }
}
