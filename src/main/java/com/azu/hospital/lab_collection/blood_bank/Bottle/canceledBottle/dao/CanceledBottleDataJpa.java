package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.dao;

import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.entity.CanceledBottles;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("canceledBottleRepo")
public class CanceledBottleDataJpa implements CanceledBottleDao {

    private final CanceledBottleRepository canceledBottleRepository;

    @Autowired
    public CanceledBottleDataJpa(CanceledBottleRepository canceledBottleRepository) {
        this.canceledBottleRepository = canceledBottleRepository;
    }

    @Override
    public void createNewCanceledBottles(CanceledBottles bottle) {
         canceledBottleRepository.save(bottle);
    }

    @Override
    public Page<CanceledBottles> getAllAndNoArchivedAndCanceledBottlesType(
            BottleTypeEnum bottleType, String bottleNumber, String fridgeNumber, String donatorName, Pageable pageable) {
        return null;
    }

    @Override
    public List<CanceledBottles> getAllByManyId(List<Long> ids) {
        return null;
    }

    @Override
    public Optional<CanceledBottles> findCanceledBottlesById(Long bottleId) {
        return Optional.empty();
    }

    @Override
    public Integer countAllByStateAndCanceledBottlesType(BloodGroupEnum bloodGroupEnum, BottleTypeEnum bottleType) {
        return null;
    }

    @Override
    public void updateCanceledBottles(CanceledBottles bottle) {

    }
}
