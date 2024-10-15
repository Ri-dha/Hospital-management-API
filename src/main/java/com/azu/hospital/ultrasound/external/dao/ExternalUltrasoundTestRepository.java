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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExternalUltrasoundTestRepository extends JpaRepository<ExternalUltrasoundTest, Long> {


    @Query(value = "SELECT COUNT(*) AS count, CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) AS hour FROM " +
            "external_ultrasound_tests" +
            " " +
            "i WHERE DATE(i.created_at) = :date GROUP BY CAST(EXTRACT(HOUR FROM i.created_at) AS INTEGER) order by " +
            "hour",
            nativeQuery =
                    true)
    List<DateTimeTest> getDateTimeTestRaw(LocalDate date);



    @Query("select r from ExternalUltrasoundTest r where lower(r.patientData.patientName) LIKE concat('%' ," +
            "lower(:patientName) " +
            ", '%') and r.state in :state and r.type in :types ")
    Page<ExternalUltrasoundTest> getAllWithFilter(
            @Param("types") List<UltrasoundTypeEnum> types ,
            @Param("state") List<UltrasoundOrderState> state,
            @Param("patientName") String patientName,
            Pageable pageable
    );

    @Query("SELECT Count(e) FROM ExternalUltrasoundTest e WHERE e.state IN :state")
    Long countAllByStateIn(@Param("state") List<UltrasoundOrderState> state);

}