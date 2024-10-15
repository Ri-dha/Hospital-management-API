package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OtherConsumablesRepository extends JpaRepository<OtherConsumables, Long>{


    @Query("SELECT o FROM OtherConsumables o WHERE o.patient.id = :patientId AND o.isArchived = false")
    List<OtherConsumables> getAllByPatientId(@Param("patientId") Long patientId);


    @Query("SELECT new com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum" +
            "(" +
            "Max(o.price),cast(Max(o.price)*(cast(count(o)AS integer ) )AS BIGDECIMAL ) ) FROM OtherConsumables o WHERE o.patient.id = :patientId")

    List<BillsDtoSum<String>> getAllWithSum(Long patientId);
}
