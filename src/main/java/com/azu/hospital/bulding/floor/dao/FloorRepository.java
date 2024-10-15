package com.azu.hospital.bulding.floor.dao;

import com.azu.hospital.bulding.floor.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FloorRepository extends JpaRepository<Floor, Long> {

    boolean existsByFloorNameContainingIgnoreCase(String floorName);
    boolean existsByFloorNumber(String floorNumber);

}
