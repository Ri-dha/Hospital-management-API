package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.dao;

import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.entity.CanceledBottles;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CanceledBottleDao {

    void createNewCanceledBottles(CanceledBottles bottle);

    Page<CanceledBottles> getAllAndNoArchivedAndCanceledBottlesType(
            BottleTypeEnum bottleType,
            String bottleNumber,
            String fridgeNumber,
            String donatorName,
            Pageable pageable
    );

    List<CanceledBottles> getAllByManyId(List<Long> ids);
    Optional<CanceledBottles> findCanceledBottlesById(Long bottleId);

    Integer countAllByStateAndCanceledBottlesType(BloodGroupEnum bloodGroupEnum, BottleTypeEnum bottleType);
    void updateCanceledBottles(CanceledBottles bottle);
}
