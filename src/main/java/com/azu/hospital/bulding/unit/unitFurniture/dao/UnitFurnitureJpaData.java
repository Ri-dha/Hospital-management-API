package com.azu.hospital.bulding.unit.unitFurniture.dao;


import com.azu.hospital.bulding.unit.unitFurniture.entity.UnitFurniture;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("unitFurnitureRepo")
public class UnitFurnitureJpaData implements UnitFurnitureDao {

    private final UnitFurnitureRepository unitFurnitureRepository;

    public UnitFurnitureJpaData(UnitFurnitureRepository unitFurnitureRepository) {
        this.unitFurnitureRepository = unitFurnitureRepository;
    }


    @Override
    public List<UnitFurniture> saveAllFurniture(List<UnitFurniture> unitFurnitures) {
        return unitFurnitureRepository.saveAll(unitFurnitures);
    }

    @Override
    public UnitFurniture insertUnitFurniture(UnitFurniture unitFurniture) {
        return unitFurnitureRepository.save(unitFurniture);
    }

    @Override
    public Page<UnitFurniture> getAllUnitFurnitureByStatusAndItemName(List<UnitInventoryRequestEnum> status, String itemName,
                                                                      Pageable pageable) {
        return unitFurnitureRepository.getAllByStatusInAndFurnitureItemNameContainingIgnoreCaseOrderByStatusDesc(status , itemName , pageable);
    }

    @Override
    public Optional<UnitFurniture> findById(Long id) {
        return unitFurnitureRepository.findById(id);
    }

    @Override
    public void updateUnitFurniture(UnitFurniture unitFurniture) {
       unitFurnitureRepository.save(unitFurniture);
    }
}
