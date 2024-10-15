package com.azu.hospital.consultant.consultantPatient.vitalSign.dao;

import com.azu.hospital.consultant.consultantPatient.vitalSign.entity.ConsultantVitalSign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ConsultantVitalSignDao {

    Optional<ConsultantVitalSign> fundById(Long id);
    ConsultantVitalSign createNewVitalSign(ConsultantVitalSign vitalSign);

    List<ConsultantVitalSign> getAllByPatientId(Long patientId);
}
