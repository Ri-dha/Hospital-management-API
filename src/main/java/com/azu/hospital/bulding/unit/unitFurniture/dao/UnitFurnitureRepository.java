package com.azu.hospital.bulding.unit.unitFurniture.dao;

import com.azu.hospital.bulding.unit.unitFurniture.entity.UnitFurniture;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitFurnitureRepository extends JpaRepository<UnitFurniture, Long> {

    Page<UnitFurniture> getAllByStatusInAndFurnitureItemNameContainingIgnoreCaseOrderByStatusDesc(List<UnitInventoryRequestEnum> status,
                                                                                                  String device_itemName, Pageable pageable);

}
