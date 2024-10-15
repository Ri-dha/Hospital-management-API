package com.azu.hospital.ecg.ecgbill.dao;


import com.azu.hospital.ecg.ecgbill.entity.EcgBill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface EcgBillRepository extends JpaRepository<EcgBill, Long> {


}
