package com.azu.hospital.lab_collection.blood_bank.Bottle.dao;

import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
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
public interface BottleRepository extends JpaRepository<Bottle, Long> {

    @Query("SELECT b FROM Bottle b " +
            "WHERE (:state is null or b.state = :state) " +
            "AND (:bottleType is null or b.bottleType = :bottleType) " +
            "AND (LOWER(b.bottleNumber) LIKE LOWER(CONCAT('%', :bottleNumber, '%'))) " +
            "AND (LOWER(b.donatorName) LIKE LOWER(CONCAT('%', :donatorName, '%'))) " +
            "ORDER BY b.createdAt DESC")
    Page<Bottle> getAllWithFilter(
            @Param("state") BottleStateEnum state,
            @Param("bottleType") BottleTypeEnum bottleType,
            @Param("bottleNumber") String bottleNumber,
            @Param("donatorName") String donatorName,
            Pageable pageable
    );


    List<Bottle> getAllByIdIn(List<Long> id);


    Integer countAllByStateAndBottleTypeAndBloodGroup(
            @Param("state") BottleStateEnum state,
            @Param("bottleType")BottleTypeEnum bottleType,
            @Param("bloodGroup")BloodGroupEnum bloodGroup
    );


    @Query("SELECT COUNT(b) FROM Bottle b WHERE b.bottleType = :bottleType")
    Long countAllByBottleType(BottleTypeEnum bottleType);

    @Query("SELECT COUNT(b) FROM Bottle b WHERE b.state = :state")
    Long countAllArchived(BottleStateEnum state);

    @Query("SELECT b FROM Bottle b WHERE " +
            "(b.state IN :states) AND " +
            "(b.bottleType = :bottleType)")
    Page<Bottle> getAllByListOfStatesAndBottleType(List<BottleStateEnum> states, BottleTypeEnum bottleType, Pageable pageable);

    @Query("SELECT b from Bottle b WHERE b.state = :bottleStateEnum")
    Page<Bottle> getAllBottlesArchive(@Param("bottleStateEnum") BottleStateEnum bottleStateEnum,Pageable pageable);

    @Query("SELECT COUNT(b) FROM Bottle b WHERE " +
            "(b.bottleType = :bottleType)")
    Long countByType(BottleTypeEnum bottleType);

    @Query("SELECT COUNT(b) FROM Bottle b WHERE " +
            "(b.bottleType = :bottleType) AND " +
            "(b.bloodGroup = :bloodGroupEnum)")
    Long countAllBottleBloodWithBloodGroup(BottleTypeEnum bottleType, BloodGroupEnum bloodGroupEnum);
}
