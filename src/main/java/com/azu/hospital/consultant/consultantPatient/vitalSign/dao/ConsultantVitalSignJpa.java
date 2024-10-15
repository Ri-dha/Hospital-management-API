package com.azu.hospital.consultant.consultantPatient.vitalSign.dao;

import com.azu.hospital.consultant.consultantPatient.vitalSign.entity.ConsultantVitalSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("consultantVitalSignJpa")
public class ConsultantVitalSignJpa implements ConsultantVitalSignDao {

    private final ConsultantVitalSignRepository vitalSignRepository;

    @Autowired
    public ConsultantVitalSignJpa(ConsultantVitalSignRepository vitalSignRepository) {
        this.vitalSignRepository = vitalSignRepository;
    }

    @Override
    public Optional<ConsultantVitalSign> fundById(Long id) {
        return vitalSignRepository.findById(id);
    }

    @Override
    public ConsultantVitalSign createNewVitalSign(ConsultantVitalSign vitalSign) {
        return vitalSignRepository.save(vitalSign);
    }

    @Override
    public List<ConsultantVitalSign> getAllByPatientId(Long patientId) {
        return vitalSignRepository.getAllByConsultantPatientIdOrderByCreatedAtDesc(patientId);
    }
}
