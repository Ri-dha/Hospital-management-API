package com.azu.hospital.hospital_info.dao;

import com.azu.hospital.hospital_info.entity.HospitalInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("hospitalInfoJpa")
public class HospitalInfoDataAccessJpa implements HospitalInfoDao{

    private final HospitalInfoRepository repository;

    public HospitalInfoDataAccessJpa(HospitalInfoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createHospitalInfo(HospitalInfo hospitalInfo) {
        repository.save(hospitalInfo);
    }

    @Override
    public void updateHospitalInfo(HospitalInfo hospitalInfo) {
        repository.save(hospitalInfo);
    }

    @Override
    public Optional<HospitalInfo> getHospitalInfoById(Long id) {
        return repository.findById(id);
    }
}
