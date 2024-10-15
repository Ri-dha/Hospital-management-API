package com.azu.hospital.radiology.radiology_bill_handler.dao;


import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface RadiologyBillRepository extends JpaRepository<RadiologyBillHandler, Long> {


    //    @Query("select r from RadiologyBillHandler r where r.type = :type")
    RadiologyBillHandler findByType(RadiologyTypeEnum type);

    @Query("select r from RadiologyBillHandler r where r.price is not null")
    Page<RadiologyBillHandler> getAllWithPrice(Pageable pageable);
}
