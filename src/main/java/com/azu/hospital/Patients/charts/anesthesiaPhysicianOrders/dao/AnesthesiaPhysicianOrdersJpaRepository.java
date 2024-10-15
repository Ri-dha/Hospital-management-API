package com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.dao;

import com.azu.hospital.Patients.charts.anesthesiaPhysicianOrders.entity.AnesthesiaPhysicianOrders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface AnesthesiaPhysicianOrdersJpaRepository
        extends JpaRepository<AnesthesiaPhysicianOrders, Long> {

    @Query("SELECT p FROM AnesthesiaPhysicianOrders p WHERE p.patient.id = :patientId")
    List<AnesthesiaPhysicianOrders> getAllAnesthesiaPhysicianOrdersByPatientId(@Param("patientId") Long patientId);
}
