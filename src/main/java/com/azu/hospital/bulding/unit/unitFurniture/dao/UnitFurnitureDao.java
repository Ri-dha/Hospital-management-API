package com.azu.hospital.bulding.unit.unitFurniture.dao;

import com.azu.hospital.bulding.unit.unitFurniture.entity.UnitFurniture;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UnitFurnitureDao {

    List<UnitFurniture> saveAllFurniture(List<UnitFurniture> unitFurnitures);

    UnitFurniture insertUnitFurniture(UnitFurniture unitFurniture);

    Page<UnitFurniture> getAllUnitFurnitureByStatusAndItemName(List<UnitInventoryRequestEnum> status , String itemName ,
                                                               Pageable pageable);
    Optional<UnitFurniture> findById(Long id);

    void updateUnitFurniture(UnitFurniture unitDevice);

}
