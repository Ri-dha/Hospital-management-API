
package com.azu.hospital.ultrasound.external.dao;

import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("externalUltrasoundTestRepo")
public class ExternalUltrasoundTestDataJpa implements ExternalUltrasoundTestDao {

    private final ExternalUltrasoundTestRepository externalUltrasoundTestRepository;

    @Autowired
    public ExternalUltrasoundTestDataJpa(ExternalUltrasoundTestRepository externalUltrasoundTestRepository) {
        this.externalUltrasoundTestRepository = externalUltrasoundTestRepository;
    }

    @Override
    public List<DateTimeTest> getDateTimeTest(LocalDate date) {
        return externalUltrasoundTestRepository.getDateTimeTestRaw(date);
    }

    @Override
    public Long countAllTests(List<UltrasoundOrderState> states) {
        return externalUltrasoundTestRepository.countAllByStateIn(states);
    }


    @Override
    public Optional<ExternalUltrasoundTest> findById(Long id) {
        return externalUltrasoundTestRepository.findById(id);
    }

    @Override
    public void updateExternalUltrasoundTest(ExternalUltrasoundTest externalUltrasoundTest) {
        externalUltrasoundTestRepository.save(externalUltrasoundTest);
    }

    @Override
    public ExternalUltrasoundTest createExternalUltrasoundTest(ExternalUltrasoundTest externalUltrasoundTest) {
        return externalUltrasoundTestRepository.save(externalUltrasoundTest);
    }

    @Override
    public Page<ExternalUltrasoundTest> getAllByFilter(List<UltrasoundTypeEnum> types, List<UltrasoundOrderState> states, String search, Pageable pageable) {
        return externalUltrasoundTestRepository.getAllWithFilter(types,states , search ,pageable);
    }





}
