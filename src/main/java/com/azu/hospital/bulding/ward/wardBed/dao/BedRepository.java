package com.azu.hospital.bulding.ward.wardBed.dao;

import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BedRepository extends JpaRepository<Bed , Long> {

    @Query("SELECT b FROM Bed b WHERE b.id = :id  AND b.patient IS NULL AND b.isDeleted = false")
    Optional<Bed> findEmptyBedByIdAndWardId(Long id);


    Page<Bed> getAllByWardWardIdAndIsDeletedIsFalse(Long ward_wardId, Pageable pageable);

    @Query("select b from Bed b where (b.patient is not null or b.prematureBaby is not null) and b.ward.wardId = :wardId and b.isDeleted = false")
    Page<Bed> getAllByWardWardIdAndPatientIsNotNull(Long wardId, Pageable pageable);


    @Query("SELECT b FROM Bed b WHERE b.patient IS NULL AND b.prematureBaby IS NULL AND b.ward.wardId = :wardId AND b.isDeleted = false")
    Page<Bed> getAllByWardWardIdAndPatientIsNull(Long wardId, Pageable pageable);


    @Query("select count(b) from Bed b where EXISTS(select p from Patient p where p.bed.id = b.id) And b.ward.wardId " +
            "= " +
            ":wardId and b.isDeleted = false")
    Integer countBedByWardWardIdAndPatientIsNotNull(Long wardId);

    @Query("select count(b) from Bed b where NOT EXISTS(select p from Patient p where p.bed.id = b.id) And b.ward" +
            ".wardId " +
            "= " +
            ":wardId and b.isDeleted = false")
    Integer countBedByWardWardIdAndPatientIsNull(Long wardId);

    Integer countBedByWardWardIdAndIsDeletedIsFalse(Long ward_wardId);

    @Query("select b from Bed b where b.ward.floor.floorId = :floorId and b.isDeleted = false")
    Page<Bed> getBedByFloorId(Long floorId, Pageable pageable);
}
