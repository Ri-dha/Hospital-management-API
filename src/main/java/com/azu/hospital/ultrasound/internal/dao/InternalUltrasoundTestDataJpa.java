package com.azu.hospital.ultrasound.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.lab_collection.request.DateTimeTest;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundBillDto;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("internalUltrasoundTestRepo")
public class InternalUltrasoundTestDataJpa implements InternalUltrasoundTestDao {

    private final InternalUltrasoundTestRepository internalUltrasoundTestRepository;

    @Autowired
    public InternalUltrasoundTestDataJpa(InternalUltrasoundTestRepository internalUltrasoundTestRepository) {
        this.internalUltrasoundTestRepository = internalUltrasoundTestRepository;
    }


    @Override
    public List<DateTimeTest> getDateTimeTest(LocalDate date) {
        return internalUltrasoundTestRepository.getDateTimeTestRaw(date);
    }

    @Override
    public Integer countAllTests(List<UltrasoundOrderState> states) {
        return internalUltrasoundTestRepository.countAllByStateIn(states);
    }


    @Override
    public Optional<InternalUltrasoundTest> findById(Long id) {
        return internalUltrasoundTestRepository.findById(id);
    }

    @Override
    public void updateInternalUltrasoundTest(InternalUltrasoundTest internalUltrasoundTest) {
        internalUltrasoundTestRepository.save(internalUltrasoundTest);
    }

    @Override
    public InternalUltrasoundTest createInternalUltrasoundTest(InternalUltrasoundTest internalUltrasoundTest) {
        return internalUltrasoundTestRepository.save(internalUltrasoundTest);
    }

    @Override
    public Page<InternalUltrasoundTest> getAllByFilter(List<UltrasoundTypeEnum> types,
                                                       List<UltrasoundOrderState> states,
                                                       String search,
                                                       Pageable pageable) {
        return internalUltrasoundTestRepository.getAllWithFilter(types,states, search, TestDestination.Hospital, pageable);
    }

    @Override
    public Page<InternalUltrasoundTest> getByPatientId(Long patientId, List<UltrasoundTypeEnum> types, List<UltrasoundOrderState> states, Pageable pageable) {
        return internalUltrasoundTestRepository.getByPatientId(patientId,types,states, pageable);
    }

    @Override
    public Page<InternalUltrasoundTest> getAllConsultantTest(Pageable pageable) {
        return internalUltrasoundTestRepository.getAllByTestDestination(TestDestination.Consultant , pageable);
    }

    @Override
    public List<InternalUltrasoundTest> getAllByConsultantPatientId(Long patientId) {
        return internalUltrasoundTestRepository.getAllByConsultantPatientIdAndTestDestination(patientId,TestDestination.Consultant);
    }

    @Override
    public Page<InternalUltrasoundTest> getAllByPatientId(Long patientId, List<UltrasoundOrderState> states,Pageable pageable) {
        return internalUltrasoundTestRepository.getAllByPatientIdAndState(patientId,states,pageable);
    }

    @Override
    public List<BillsDtoSum<UltrasoundTypeEnum>> getInternalUltrasoundSum(Long PatientId) {
        return internalUltrasoundTestRepository.sumAllInternalUltrasoundQuantityForSamePatientId(PatientId);
    }

    @Override
    public List<InternalUltrasoundTest> getAllCompletedByPatientId(Long patientId) {
        return internalUltrasoundTestRepository.getAllCompletedByPatientId(patientId);
    }

//    @Override
//    public Integer countAllByPatientId(Long patientId , UltrasoundTypeEnum type) {
//        return internalUltrasoundTestRepository.countAllByPatientId(patientId,type);
//    }


}
