package com.azu.hospital.bulding.ward.wardBed.dao;

import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BedDao {


    Bed createNewBed(Bed bed);
    Optional<Bed> getBedById(Long id);
    Optional<Bed> getBedByIdAndWardIdAndBedNotReserved(Long id);

    Page<Bed> getAllBed(Pageable pageable);

    Page<Bed> getAllBedByWardId(Long wardId , Pageable pageable);
    Page<Bed> getAllBedByWardIdAndIsReserved(Long wardId  ,  Pageable pageable);
    Page<Bed> getAllBedByWardIdAndIsNotReserved(Long wardId  ,  Pageable pageable);

    Integer countAllBed(Long wardId);
    Integer countOfBedReserved(Long wardId);
    Integer countOfBedNotReserved(Long wardId);

    void updateBed(Bed bed);
    void deleteBedById(Long id);

    Page<Bed> getBedByFloorId(Long floorId , Pageable pageable);
}
