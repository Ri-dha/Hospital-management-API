package com.azu.hospital.bulding.ward.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.bulding.ward.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WardDao {

    Optional<Ward> findWardById(Long id);

    List<Ward> findAllWardByDepartmentId(Long id);

    Page<Ward> findAllWard(String wardName, Pageable pageable);

    void createWard(Ward ward);

    Boolean existsByManagerId(Long managerId);

    Boolean existsByAssistanceId(Long assistanceId);

    void updateWard(Ward ward);

    List<Ward> getAllWardByFloorId(Long floorId);

    List<Doctor> getDoctorsByWardId(Long wardId);

    List<Nurse> getNursesByWardId(Long wardId);

    Optional<Ward> getWardBuNurseId(Long nurseId);
}
