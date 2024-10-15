package com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao;

import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface BottleOrderRepository extends JpaRepository<BottleOrder , Long> {


    @Query("SELECT bo FROM BottleOrder bo " +
            "WHERE bo.state = :state " +
            "AND LOWER(bo.patient.patientData.fullName) LIKE LOWER(CONCAT('%', COALESCE(:patientName, ''), '%')) " +
            "ORDER BY bo.createdAt ASC, bo.updatedAt ASC")
    Page<BottleOrder> getAllByStateAndPatientPatientDataFullNameContainingIgnoreCaseOrderByCreatedAtAscUpdatedAt(
            @Param("state") BottleStateEnum state,
            @Param("patientName") String patientName,
            Pageable pageable
    );


    @Query("SELECT bo FROM BottleOrder bo " +
            "WHERE bo.state = :state " +
            "AND bo.isReserved = :isReserved " +
            "AND LOWER(bo.patient.patientData.fullName) LIKE LOWER(CONCAT('%', COALESCE(:patientName, ''), '%')) " +
            "ORDER BY bo.createdAt DESC , bo.updatedAt DESC")
    Page<BottleOrder> getAllByStateAndIsReservedAndPatientPatientDataFullNameContainingIgnoreCaseOrderByPatientMedicalHistoryTriage(
            @Param("state") BottleStateEnum state,
            @Param("isReserved") Boolean isReserved,
            @Param("patientName") String patientName,
            Pageable pageable
    );


    List<BottleOrder> findTop6ByStateOrderByCreatedAtDesc(BottleStateEnum state);


    @Query("SELECT bo FROM BottleOrder bo " +
            "WHERE bo.state = :state " +
            "AND (LOWER(bo.patient.patientData.fullName) LIKE LOWER(CONCAT('%', COALESCE(:search, ''), '%')) OR :search IS NULL) " +
            "ORDER BY bo.createdAt DESC")
    Page<BottleOrder> getAllNewRequestWithFilter(
            @Param("state") BottleStateEnum state,
            @Param("search") String search,
            Pageable pageable
    );


    @Query("SELECT SUM(b.count) FROM BottleOrder b WHERE b.state = :state AND" +
                " b.bloodGroup = :bloodGroup AND " +
                "b.bottleType = :bottleType")
        Integer sumCountByBottleOrderStateAndBloodGroupAndBottleType(
                BottleStateEnum state,
                @Param("bloodGroup") BloodGroupEnum bloodGroup,
                @Param("bottleType") BottleTypeEnum bottleType
        );

    @Query("SELECT bo FROM BottleOrder bo WHERE bo.bottleType = :bottleType AND bo.state IN :states")
    Page<BottleOrder> findByBottleTypeAndStateIn(
            @Param("bottleType") BottleTypeEnum bottleType,
            @Param("states") List<BottleStateEnum> states,
            Pageable pageable);
}
