package com.azu.hospital.bulding.ward.wardFurniture.dao;

import com.azu.hospital.bulding.ward.wardFurniture.entity.WardFurniture;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WardFurnitureDao {

    List<WardFurniture> saveAllFurniture(List<WardFurniture> wardFurnitures);

    WardFurniture insertWardFurniture(WardFurniture wardFurniture);

    Page<WardFurniture> getAllWardFurnitureByStatusAndItemName(List<WardInventoryRequestEnum> status , String itemName ,
                                                         Pageable pageable);
    Optional<WardFurniture> findById(Long id);

    void updateWardFurniture(WardFurniture wardDevice);

}
