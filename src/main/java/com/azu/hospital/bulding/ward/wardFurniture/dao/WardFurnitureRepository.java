package com.azu.hospital.bulding.ward.wardFurniture.dao;

import com.azu.hospital.bulding.ward.wardFurniture.entity.WardFurniture;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardFurnitureRepository extends JpaRepository<WardFurniture, Long> {

    Page<WardFurniture> getAllByStatusInAndFurnitureItemNameContainingIgnoreCaseOrderByStatusDesc(List<WardInventoryRequestEnum> status,
                                                                                            String device_itemName, Pageable pageable);

}
