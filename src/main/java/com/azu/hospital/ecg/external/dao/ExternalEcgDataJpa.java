package com.azu.hospital.ecg.external.dao;

import com.azu.hospital.ecg.external.entity.ExternalEcg;
import com.azu.hospital.ecg.internal.dao.EcgDao;
import com.azu.hospital.ecg.internal.dao.EcgRepository;
import com.azu.hospital.ecg.internal.entity.Ecg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("externalEcgJpa")
public class ExternalEcgDataJpa implements ExternalEcgDao {


    private final ExternalEcgRepository externalEcgRepository;

    @Autowired
    public ExternalEcgDataJpa(ExternalEcgRepository externalEcgRepository) {
        this.externalEcgRepository = externalEcgRepository;
    }

    @Override
    public Optional<ExternalEcg> findTestById(Long id) {
        return externalEcgRepository.findById(id);
    }

    @Override
    public List<ExternalEcg> getAllByNurseId(Long id) {
        return externalEcgRepository.getEcgByNurseNurseIdOrderByCreatedAtDesc(id);
    }

    @Override
    public List<ExternalEcg> getAllByPatientId() {
        return externalEcgRepository.getEcgByOrderByCreatedAtDesc();
    }

    @Override
    public ExternalEcg createNewEcgTest(ExternalEcg ecg) {
        return externalEcgRepository.save(ecg);
    }

    @Override
    public ExternalEcg updateWitDataEcgTest(ExternalEcg ecg) {
        return externalEcgRepository.save(ecg);
    }

    @Override
    public void updateEcgTest(ExternalEcg ecg) {
        externalEcgRepository.save(ecg);
    }
}
