package com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDtoScreen;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
public interface GetPatientExpanseFinalList {
    ExpanseDtoScreen getPatientExpanseByPatientId(Long patientId);

    ExpanseDtoScreen getArchivedPatientExpanseByPatientId(Long patientId);

    ExpanseDtoScreen GetPatientExpanseFinalListByIdAndDate(Long patientId, LocalDate date);


}
