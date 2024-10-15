package com.azu.hospital.lab_collection.blood_bank.Bottle.dao;

import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BottleDao {

    void createNewBottle(Bottle bottle);

    Page<Bottle> getAllAndNoArchivedAndBottleType(
            BottleTypeEnum bottleType,
            String bottleNumber,
            String donatorName,
            Pageable pageable
    );


    List<Bottle> getAllByManyId(List<Long> ids);

    Optional<Bottle> findBottleById(Long bottleId);

    Integer countAllByStateAndBottleType(BloodGroupEnum bloodGroupEnum, BottleTypeEnum bottleType);

    void updateBottle(Bottle bottle);

    Long countAllByType(BottleTypeEnum bottleType);

    Long countAllArchived(BottleStateEnum state);

    Page<Bottle> getAllByListOfStatesAndBottleType(List<BottleStateEnum> states, BottleTypeEnum bottleType, Pageable pageable);

    Page<Bottle> getAllBottlesArchive(BottleStateEnum state,Pageable pageable);

    Long countAllBYTypeAndGroup(BottleTypeEnum bottleType, BloodGroupEnum bloodGroupEnum);

    Long countByType(BottleTypeEnum bottleType);


}
