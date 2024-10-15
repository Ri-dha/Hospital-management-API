package com.azu.hospital.radiology.internal.dao;

import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("internalRadiologyTestRepo")
public class InternalRadiologyTestDataJpa implements InternalRadiologyTestDao {

    private final InternalRadiologyTestRepository internalRadiologyTestRepository;

    @Autowired
    public InternalRadiologyTestDataJpa(InternalRadiologyTestRepository internalRadiologyTestRepository) {
        this.internalRadiologyTestRepository = internalRadiologyTestRepository;
    }

    @Override
    public List<DateTimeTest> getDateTimeTest(LocalDate date) {
        return internalRadiologyTestRepository.getDateTimeTestRaw(date);
    }

    @Override
    public Integer countOfTests(List<RadiologyOrderState> states, List<RadiologyTypeEnum> type) {
        return internalRadiologyTestRepository.countAllByStateInAndTypeIn(states, type);
    }

    @Override
    public Optional<InternalRadiologyTest> getInternalRadiologyTestById(Long id) {
        return internalRadiologyTestRepository.findById(id);
    }

    @Override
    public void updateInternalRadiologyTest(InternalRadiologyTest internalRadiologyTest) {
        internalRadiologyTestRepository.save(internalRadiologyTest);
    }

    @Override
    public InternalRadiologyTest createInternalRadiologyTest(InternalRadiologyTest internalRadiologyTest) {
        return internalRadiologyTestRepository.save(internalRadiologyTest);
    }

    @Override
    public Page<InternalRadiologyTest> getAllByFilter(
            List<RadiologyTypeEnum> types,
            String search,
            List<RadiologyOrderState> states,
            Pageable pageable) {

        return internalRadiologyTestRepository.getAllWithFilter(
                types,
                search,
                states,
                TestDestination.Hospital,
                pageable
        );
    }

    @Override
    public Page<InternalRadiologyTest> getAllConsultantTest(Pageable pageable) {
        return internalRadiologyTestRepository.getAllByTestDestination(TestDestination.Consultant, pageable);
    }

    @Override
    public Page<InternalRadiologyTest> getByPatientId(Long patientId,List<RadiologyTypeEnum> types ,List<RadiologyOrderState> states,Pageable pageable) {
        return internalRadiologyTestRepository.getByPatientId(patientId ,types ,states,pageable);
    }

    @Override
    public List<InternalRadiologyTest> getAllByConsultantPatientId(Long patientId) {
        return internalRadiologyTestRepository.getAllByConsultantPatientIdAndTestDestination(patientId, TestDestination.Consultant);
    }

    @Override
    public List<InternalRadiologyTest> getAllCompletedByPatientId(Long patientId, List<RadiologyOrderState> states) {
        return internalRadiologyTestRepository.getAllByPatientIdAndState(patientId,states);
    }

    @Override
    public List<BillsDtoSum<RadiologyTypeEnum>> sumAllDrugsItemQuantityForSamePatientId(Long patientId) {
        return internalRadiologyTestRepository.sumAllInternalRadiologyQuantityForSamePatientId(patientId);
    }

    @Override
    public List<InternalRadiologyTest> getAllCompletedByPatientId(Long patientId) {
        return internalRadiologyTestRepository.findCompletedRadiologyTestsByPatientId(patientId);
    }

    @Override
    public Integer countAllByPatientId(Long patientId, RadiologyTypeEnum type) {
        return internalRadiologyTestRepository.countAllByPatientId(patientId,type);
    }

}
