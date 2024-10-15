package com.azu.hospital.hospital_info.dao;

import com.azu.hospital.hospital_info.entity.HospitalInfo;

import java.util.Optional;

public interface HospitalInfoDao {

  void createHospitalInfo(HospitalInfo hospitalInfo);

  void updateHospitalInfo(HospitalInfo hospitalInfo);

  Optional<HospitalInfo> getHospitalInfoById(Long id);





}
