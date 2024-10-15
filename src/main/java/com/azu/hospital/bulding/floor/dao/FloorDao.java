package com.azu.hospital.bulding.floor.dao;


import com.azu.hospital.bulding.floor.entity.Floor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FloorDao {

    Page<Floor> selectAllFloors(Pageable pageable);
    Optional<Floor> selectFloorById(Long floorId);
    Floor insertFloor(Floor request);
    void updateFloor(Floor update);
    void deleteFloorById(Long floorId);


    boolean existsByFloorNameContainingIgnoreCase(String floorName);
    boolean existsByFloorNumber(String floorNumber);

    Page<Floor> getAllFloors(Pageable pageable);

}
