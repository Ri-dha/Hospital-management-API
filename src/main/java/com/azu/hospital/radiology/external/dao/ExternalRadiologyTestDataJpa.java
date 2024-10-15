package com.azu.hospital.radiology.external.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("externalRadiologyTestRepo")
public class ExternalRadiologyTestDataJpa implements ExternalRadiologyTestDao {

    private final ExternalRadiologyTestRepository externalRadiologyTestRepository;

    @Autowired
    public ExternalRadiologyTestDataJpa(ExternalRadiologyTestRepository externalRadiologyTestRepository) {
        this.externalRadiologyTestRepository = externalRadiologyTestRepository;
    }

    @Override
    public List<DateTimeTest> getDateTimeTest(LocalDate date) {
        return externalRadiologyTestRepository.getDateTimeTestRaw(date);
    }

    @Override
    public Long countOfTests(List<RadiologyOrderState> states, List<RadiologyTypeEnum> types) {
        return externalRadiologyTestRepository.countAllByStateInAndTypeIn(states, types);
    }

    @Override
    public Optional<ExternalRadiologyTest> findById(Long id) {
        return externalRadiologyTestRepository.findById(id);
    }

    @Override
    public void updateExternalRadiologyTest(ExternalRadiologyTest externalRadiologyTest) {
        externalRadiologyTestRepository.save(externalRadiologyTest);
    }

    @Override
    public ExternalRadiologyTest createExternalRadiologyTest(ExternalRadiologyTest externalRadiologyTest) {
        return externalRadiologyTestRepository.save(externalRadiologyTest);
    }

    @Override
    public Page<ExternalRadiologyTest> getAllByFilter(
            List<RadiologyTypeEnum> types ,
            List<RadiologyOrderState> states,
            String search,
            Pageable pageable
    ) {


        return externalRadiologyTestRepository.getAllWithFilter(types , states, search, pageable);
    }

    @Override
    public List<BillsDtoSum> getAllWithSum(Long patientId) {
        return externalRadiologyTestRepository.sumAllExternalRadiologyQuantityForSamePatientId(patientId);
    }


}
