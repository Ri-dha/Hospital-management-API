package com.azu.hospital.bulding.ward.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.bulding.ward.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface WardRepository extends JpaRepository<Ward, Long> {

    @Query("SELECT w FROM Ward  w WHERE w.department.depId = :depId")
    List<Ward> findAllByDepartmentDepId(@Param("depId") Long depId);

    @Query("SELECT f FROM Ward f WHERE f.floor.floorId = :floorId")
    List<Ward> findAllByFloorFloorId(@Param("floorId") Long floorId);

    Page<Ward> getAllByNameContainingIgnoreCase(String name , Pageable pageable);

    boolean existsByDoctorId(Long manager_id);

    boolean existsByNurseId(Long managerAssistance_id);

    @Query("SELECT w.doctor FROM Ward w WHERE w.wardId = :wardId")
    List<Doctor> findDoctorsByWardId(Long wardId);

    @Query("SELECT w.nurse FROM Ward w WHERE w.wardId = :wardId")
    List<Nurse> findNursesByWardId(Long wardId);

    @Query("SELECT w FROM Ward w WHERE w.nurse.id = :nurseId")
    Optional<Ward> findWardByNurseNurseId(Long nurseId);
}
