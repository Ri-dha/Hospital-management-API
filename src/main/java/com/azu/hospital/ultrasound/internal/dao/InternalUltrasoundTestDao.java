package com.azu.hospital.ultrasound.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundBillDto;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InternalUltrasoundTestDao {

    List<DateTimeTest> getDateTimeTest(LocalDate date);
    Integer countAllTests(List<UltrasoundOrderState> states);
    Optional<InternalUltrasoundTest> findById(Long id);

    void updateInternalUltrasoundTest(InternalUltrasoundTest internalUltrasoundTest);
    InternalUltrasoundTest createInternalUltrasoundTest(InternalUltrasoundTest internalUltrasoundTest);



    Page<InternalUltrasoundTest> getAllByFilter(
            List<UltrasoundTypeEnum> types,
            List<UltrasoundOrderState> states,
            String search,
            Pageable pageable);

    Page<InternalUltrasoundTest> getAllConsultantTest(Pageable pageable);

    Page<InternalUltrasoundTest> getByPatientId(Long patientId, List<UltrasoundTypeEnum> types, List<UltrasoundOrderState> states, Pageable pageable);

    List<InternalUltrasoundTest> getAllByConsultantPatientId(Long patientId);


    Page<InternalUltrasoundTest> getAllByPatientId(Long patientId, List<UltrasoundOrderState> states,Pageable pageable);

    List<BillsDtoSum<UltrasoundTypeEnum>> getInternalUltrasoundSum(Long PatientId);


    List<InternalUltrasoundTest> getAllCompletedByPatientId(Long patientId);

//    Integer countAllByPatientId(Long patientId, UltrasoundTypeEnum type);
}
