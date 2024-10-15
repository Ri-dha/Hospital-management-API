package com.azu.hospital.bulding.ward.wardBed.dao;

import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("bedRepo")
public class BedDateJpa implements BedDao {

    private final BedRepository bedRepository;

    public BedDateJpa(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Override
    public Bed createNewBed(Bed bed) {
        return bedRepository.save(bed);
    }

    @Override
    public Optional<Bed> getBedById(Long id) {
        return bedRepository.findById(id);
    }

    @Override
    public Optional<Bed> getBedByIdAndWardIdAndBedNotReserved(Long id) {
        return bedRepository.findEmptyBedByIdAndWardId(id);
    }

    @Override
    public Page<Bed> getAllBed(Pageable pageable) {
        return bedRepository.findAll(pageable);
    }

    @Override
    public Page<Bed> getAllBedByWardId(Long wardId, Pageable pageable) {
        return bedRepository.getAllByWardWardIdAndIsDeletedIsFalse(wardId , pageable);
    }

    @Override
    public Page<Bed> getAllBedByWardIdAndIsReserved(Long wardId, Pageable pageable) {

        return bedRepository.getAllByWardWardIdAndPatientIsNotNull(wardId,pageable);
    }

    @Override
    public Page<Bed> getAllBedByWardIdAndIsNotReserved(Long wardId, Pageable pageable) {
        return bedRepository.getAllByWardWardIdAndPatientIsNull(wardId,pageable);
    }

    @Override
    public Integer countAllBed(Long wardId) {
        return bedRepository.countBedByWardWardIdAndIsDeletedIsFalse(wardId);
    }

    @Override
    public Integer countOfBedReserved(Long wardId) {
        return bedRepository.countBedByWardWardIdAndPatientIsNotNull(wardId);
    }

    @Override
    public Integer countOfBedNotReserved(Long wardId) {
        return bedRepository.countBedByWardWardIdAndPatientIsNull(wardId);
    }

    @Override
    public void updateBed(Bed bed) {
        bedRepository.save(bed);
    }

    @Override
    public void deleteBedById(Long id) {
        bedRepository.deleteById(id);
    }

    @Override
    public Page<Bed> getBedByFloorId(Long floorId, Pageable pageable) {
        return bedRepository.getBedByFloorId(floorId,pageable);
    }
}
