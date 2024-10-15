package com.azu.hospital.hospital_info.dao;

import com.azu.hospital.hospital_info.entity.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {

}
