package com.azu.hospital.ecg.external.dao;

import com.azu.hospital.ecg.external.entity.ExternalEcg;

import java.util.List;
import java.util.Optional;

public interface ExternalEcgDao {

    Optional<ExternalEcg> findTestById(Long id);
    List<ExternalEcg> getAllByNurseId(Long id);
    List<ExternalEcg> getAllByPatientId();
    ExternalEcg createNewEcgTest(ExternalEcg ecg);
    ExternalEcg updateWitDataEcgTest(ExternalEcg ecg);
    void updateEcgTest(ExternalEcg ecg);

}
