package com.azu.hospital.bulding.ward.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.bulding.ward.entity.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("wardRepo")
public class WardJpaAccess implements WardDao {

    private final WardRepository wardRepository;

    @Autowired
    public WardJpaAccess(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    @Override
    public Optional<Ward> findWardById(Long id) {
        return wardRepository.findById(id);
    }

    @Override
    public List<Ward> findAllWardByDepartmentId(Long id) {
        return wardRepository.findAllByDepartmentDepId(id);
    }

    @Override
    public Page<Ward> findAllWard(String wardName, Pageable pageable) {
        return wardRepository.getAllByNameContainingIgnoreCase(wardName , pageable);
    }

    @Override
    public void createWard(Ward ward) {
        wardRepository.save(ward);
    }

    @Override
    public Boolean existsByManagerId(Long managerId) {
        return wardRepository.existsByDoctorId(managerId);
    }

    @Override
    public Boolean existsByAssistanceId(Long assistanceId) {
        return wardRepository.existsByNurseId(assistanceId);
    }

    @Override
    public void updateWard(Ward ward) {
         wardRepository.save(ward);
    }

    @Override
    public List<Ward> getAllWardByFloorId(Long floorId) {
        return wardRepository.findAllByFloorFloorId(floorId);
    }

    @Override
    public List<Doctor> getDoctorsByWardId(Long wardId) {
        return wardRepository.findDoctorsByWardId(wardId);
    }

    @Override
    public List<Nurse> getNursesByWardId(Long wardId) {
        return wardRepository.findNursesByWardId(wardId);
    }

    @Override
    public Optional<Ward> getWardBuNurseId(Long nurseId) {
        return wardRepository.findWardByNurseNurseId(nurseId);
    }


}
