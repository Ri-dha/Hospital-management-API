package com.azu.hospital.all_user_sevices.employee.nurses.dao;


import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("NurseJpa")
public class NurseJPADataAccess implements NurseDao {

    private final NurseRepository nurseRepository;

    @Autowired
    public NurseJPADataAccess(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @Override
    public Nurse createNewNurse(Nurse request) {
        return nurseRepository.save(request);
    }

    @Override
    public Page<Nurse> getAllNurses(Pageable pageable) {
        return nurseRepository.findAll(pageable);
    }


    @Override
    public void updateCurrentNurse(Nurse update) {
        nurseRepository.save(update);
    }


    @Override
    public void deleteExistNurse(Long nurseId) {
        nurseRepository.deleteById(nurseId);
    }

    @Override
    public Optional<Nurse> findNurseById(Long id) {
        return  nurseRepository.findNurseById(id);
    }

    @Override
    public Optional<Nurse> findByUsername(String username) {
        return nurseRepository.findByUsername(username);
    }

    @Override
    public Optional<Nurse> findByEmail(String email) {
        return nurseRepository.findByEmail(email);
    }


    @Override
    public Boolean existsByUsername(String username) {
        return nurseRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return nurseRepository.existsByEmail(email);
    }

    @Override
    public List<Nurse> findByUsernameContainingOrEmailContaining(String username, String email) {
        return nurseRepository.findByUsernameContainingOrEmailContaining(username, email);
    }


    @Override
    public Boolean existsByMobile(String mobile) {
        return nurseRepository.existsByMobile(mobile);
    }

    @Override
    public Page<Nurse> findAllNursesBy(
            String username, String email,
            String specialist,
            String bloodGroup, String mobile, String gender, Pageable pageable
    ) {
        return nurseRepository.findNursesBy(username, email, bloodGroup,bloodGroup, mobile, gender,
                pageable);
    }

    @Override
    public Optional<Nurse> getNurseByToken(String token) {
        return Optional.empty();
                //nurseRepository.findNurseByTokens(token);
    }

    @Override
    public Long countAllItems() {
        return nurseRepository.count();
    }




}
