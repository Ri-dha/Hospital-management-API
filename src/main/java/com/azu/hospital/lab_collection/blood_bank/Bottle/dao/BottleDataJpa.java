package com.azu.hospital.lab_collection.blood_bank.Bottle.dao;

import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("bottleRepo")
public class BottleDataJpa implements BottleDao{

    private final BottleRepository bottleRepository;

    @Autowired
    public BottleDataJpa(BottleRepository bottleRepository) {
        this.bottleRepository = bottleRepository;
    }

    @Override
    public void createNewBottle(Bottle bottle) {
         bottleRepository.save(bottle);
    }

    @Override
    public Page<Bottle> getAllAndNoArchivedAndBottleType(
            BottleTypeEnum bottleType,
            String bottleNumber,
            String donatorName,
            Pageable pageable
    ) {
        return bottleRepository.getAllWithFilter(
                BottleStateEnum.NoArchive,
                bottleType ,
                bottleNumber,
                donatorName,
                pageable
        );

    }

    @Override
    public List<Bottle> getAllByManyId(List<Long> ids) {
        return bottleRepository.getAllByIdIn(ids);
    }


    @Override
    public Optional<Bottle> findBottleById(Long bottleId) {
        return bottleRepository.findById(bottleId);
    }

    @Override
    public Integer countAllByStateAndBottleType(BloodGroupEnum bloodGroupEnum, BottleTypeEnum bottleType) {
        return bottleRepository.countAllByStateAndBottleTypeAndBloodGroup(
                BottleStateEnum.NoArchive,
                bottleType,
                bloodGroupEnum
        );
    }

    @Override
    public void updateBottle(Bottle bottle) {
        bottleRepository.save(bottle);
    }

    @Override
    public Long countAllByType(BottleTypeEnum bottleType) {
        return bottleRepository.countAllByBottleType(bottleType);
    }

    @Override
    public Long countAllArchived(BottleStateEnum state) {
        return bottleRepository.countAllArchived(state);
    }

    @Override
    public Page<Bottle> getAllByListOfStatesAndBottleType(List<BottleStateEnum> states, BottleTypeEnum bottleType, Pageable pageable) {
        return bottleRepository.getAllByListOfStatesAndBottleType(states, bottleType, pageable);
    }

    @Override
    public Page<Bottle> getAllBottlesArchive(BottleStateEnum state,Pageable pageable) {
        return bottleRepository.getAllBottlesArchive(state,pageable);
    }

    @Override
    public Long countAllBYTypeAndGroup(BottleTypeEnum bottleType, BloodGroupEnum bloodGroupEnum) {
        return bottleRepository.countAllBottleBloodWithBloodGroup(bottleType, bloodGroupEnum);
    }

    @Override
    public Long countByType(BottleTypeEnum bottleType) {
        return bottleRepository.countByType(bottleType);
    }

}
