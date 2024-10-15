package com.azu.hospital.ecg.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ecgJpa")
public class EcgDataJpa implements EcgDao{


    private final EcgRepository ecgRepository;

    @Autowired
    public EcgDataJpa(EcgRepository ecgRepository) {
        this.ecgRepository = ecgRepository;
    }

    @Override
    public Optional<Ecg> findTestById(Long id) {
        return ecgRepository.findById(id);
    }

    @Override
    public Page<Ecg> getWithFilter(String search, List<EcgStates> ecgStates, Pageable pageable) {
        return ecgRepository.getAllWithFilter(TestDestination.Consultant,search,ecgStates,pageable);
    }

    @Override
    public Page<Ecg> getAllByPatientId(Long id,Pageable pageable) {
        return ecgRepository.getAllByPatientId(id, pageable);
    }

    @Override
    public List<Ecg> getAllByConsultantPatientId(Long id) {
        return ecgRepository.getAllByConsultantPatientId(id);
    }

    @Override
    public Page<Ecg> getAllConsultantTest(Pageable pageable) {
        return ecgRepository.getAllByTestDestination(TestDestination.Consultant , pageable);
    }

    @Override
    public Ecg createNewEcgTest(Ecg ecg) {
        return ecgRepository.save(ecg);
    }

    @Override
    public Ecg updateWitDataEcgTest(Ecg ecg) {
        return ecgRepository.save(ecg);
    }

    @Override
    public void updateEcgTest(Ecg ecg) {
        ecgRepository.save(ecg);
    }

    @Override
    public List<Ecg> getAllCompletedEcgTestByPatientId(Long patientId) {
        return ecgRepository.getAllCompleteByPatientId(patientId);
    }

    @Override
    public List<BillsDtoSum<String>> findSumByPatientId(Long patientId) {
        return ecgRepository.findSumByPatientId(patientId);
    }

    @Override
    public Integer countAllByPatientId(Long patientId) {
        return ecgRepository.countAllByPatientId(patientId);
    }
}
