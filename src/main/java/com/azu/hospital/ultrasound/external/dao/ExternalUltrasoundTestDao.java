package com.azu.hospital.ultrasound.external.dao;

import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExternalUltrasoundTestDao {

    List<DateTimeTest> getDateTimeTest(LocalDate date);
    Long countAllTests(List<UltrasoundOrderState> states);
    Optional<ExternalUltrasoundTest> findById(Long id);

    void updateExternalUltrasoundTest(ExternalUltrasoundTest externalUltrasoundTest);
    ExternalUltrasoundTest createExternalUltrasoundTest(ExternalUltrasoundTest externalUltrasoundTest);

    Page<ExternalUltrasoundTest> getAllByFilter(
            List<UltrasoundTypeEnum> types ,
            List<UltrasoundOrderState> states,
            String search,
            Pageable pageable
    );


}
