package com.azu.hospital.ultrasound.ultrasoundBill.dao;


import com.azu.hospital.ultrasound.ultrasoundBill.entity.UltrasoundBill;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface UltrasoundBillRepository extends JpaRepository<UltrasoundBill, Long> {

    @Query("select u from UltrasoundBill u where u.type = :type")
    UltrasoundBill findByType(UltrasoundTypeEnum type);
}
