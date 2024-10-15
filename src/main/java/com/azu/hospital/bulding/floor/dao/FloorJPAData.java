package com.azu.hospital.bulding.floor.dao;

import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("floorJpa")
public class FloorJPAData implements FloorDao {

    private final FloorRepository floorRepository;

    public FloorJPAData(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Override
    public Page<Floor> selectAllFloors(Pageable pageable) {
        return floorRepository.findAll(pageable);
    }

    @Override
    public Optional<Floor> selectFloorById(Long floorId) {
        return floorRepository.findById(floorId);
    }

    @Override
    public Floor insertFloor(Floor request) {
      return floorRepository.save(request);

    }

    @Override
    public void updateFloor(Floor update) {
      floorRepository.save(update);
    }

    @Override
    public void deleteFloorById(Long floorId) {
        Floor floor = floorRepository.findById(floorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Floor Id " + floorId + "Not Found"
                        )
                );
      floorRepository.deleteById(floor.getFloorId());
    }


    @Override
    public boolean existsByFloorNameContainingIgnoreCase(String floorName) {
        return floorRepository.existsByFloorNameContainingIgnoreCase(floorName);
    }

    @Override
    public boolean existsByFloorNumber(String floorNumber) {
        return floorRepository.existsByFloorNumber(floorNumber);

    }

    @Override
    public Page<Floor> getAllFloors(Pageable pageable) {
        return floorRepository.findAll(pageable);
    }
}
