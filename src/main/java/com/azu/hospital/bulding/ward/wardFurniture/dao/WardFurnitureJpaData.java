package com.azu.hospital.bulding.ward.wardFurniture.dao;


import com.azu.hospital.bulding.ward.wardFurniture.entity.WardFurniture;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("wardFurnitureRepo")
public class WardFurnitureJpaData implements WardFurnitureDao {

    private final WardFurnitureRepository wardFurnitureRepository;

    @Autowired
    public WardFurnitureJpaData(WardFurnitureRepository wardFurnitureRepository) {
        this.wardFurnitureRepository = wardFurnitureRepository;
    }


    @Override
    public List<WardFurniture> saveAllFurniture(List<WardFurniture> wardFurnitures) {
        return wardFurnitureRepository.saveAll(wardFurnitures);
    }

    @Override
    public WardFurniture insertWardFurniture(WardFurniture wardFurniture) {
        return wardFurnitureRepository.save(wardFurniture);
    }

    @Override
    public Page<WardFurniture> getAllWardFurnitureByStatusAndItemName(
            List<WardInventoryRequestEnum> status, String itemName,
                                                          Pageable pageable) {
        return wardFurnitureRepository.getAllByStatusInAndFurnitureItemNameContainingIgnoreCaseOrderByStatusDesc(status , itemName , pageable);
    }

    @Override
    public Optional<WardFurniture> findById(Long id) {
        return wardFurnitureRepository.findById(id);
    }

    @Override
    public void updateWardFurniture(WardFurniture wardFurniture) {
       wardFurnitureRepository.save(wardFurniture);
    }
}
