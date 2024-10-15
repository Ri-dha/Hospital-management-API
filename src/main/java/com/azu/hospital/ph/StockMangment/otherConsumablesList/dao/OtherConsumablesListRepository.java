package com.azu.hospital.ph.StockMangment.otherConsumablesList.dao;

import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OtherConsumablesListRepository extends JpaRepository<OtherConsumablesList, Long> {



    @Query("select ocl from OtherConsumablesList ocl where ocl.patient.id = :patientId")
    List<OtherConsumablesList> getAllByPatientId(Long patientId);
}
