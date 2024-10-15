package com.azu.hospital.ecg.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EcgDao {

    Optional<Ecg> findTestById(Long id);
    Page<Ecg> getWithFilter(

            String search,
            List<EcgStates>ecgStates,
            Pageable pageable);

    Page<Ecg> getAllByPatientId(Long id,Pageable pageable);
    List<Ecg> getAllByConsultantPatientId(Long id);
    Page<Ecg> getAllConsultantTest(Pageable pageable);
    Ecg createNewEcgTest(Ecg ecg);
    Ecg updateWitDataEcgTest(Ecg ecg);
    void updateEcgTest(Ecg ecg);

    List<Ecg> getAllCompletedEcgTestByPatientId(Long patientId);

    List<BillsDtoSum<String>> findSumByPatientId(Long patientId);

    Integer countAllByPatientId(Long patientId);
}
