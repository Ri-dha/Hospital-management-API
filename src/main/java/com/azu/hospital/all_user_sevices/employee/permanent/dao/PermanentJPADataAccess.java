package com.azu.hospital.all_user_sevices.employee.permanent.dao;


import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("PermanentJpa")
public class PermanentJPADataAccess implements PermanentDao {

    private final PermanentRepository permanentRepository;


    @Autowired
    public PermanentJPADataAccess(PermanentRepository permanentRepository) {
        this.permanentRepository = permanentRepository;
    }

    @Override
    public Permanent createNewPermanent(Permanent request) {
        return permanentRepository.save(request);
    }

    @Override
    public Page<Permanent> getAllPermanents(Pageable pageable) {
        return permanentRepository.findAll(pageable);
    }


    @Override
    public void updateCurrentPermanent(Permanent update) {
        permanentRepository.save(update);
    }


    @Override
    public void deleteExistPermanent(Long permanentId) {
        permanentRepository.deleteById(permanentId);
    }

    @Override
    public Optional<Permanent> findPermanentById(Long id) {
        return  permanentRepository.findPermanentById(id);
    }

    @Override
    public Optional<Permanent> findByUsername(String username) {
        return permanentRepository.findByUsername(username);
    }

    @Override
    public Optional<Permanent> findByEmail(String email) {
        return permanentRepository.findByEmail(email);
    }


    @Override
    public Boolean existsByUsername(String username) {
        return permanentRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return permanentRepository.existsByEmail(email);
    }

    @Override
    public List<Permanent> findByUsernameContainingOrEmailContaining(String username, String email) {
        return permanentRepository.findByUsernameContainingOrEmailContaining(username, email);
    }


    @Override
    public Boolean existsByMobile(String mobile) {
        return permanentRepository.existsByMobile(mobile);
    }

    @Override
    public Page<Permanent> findAllPermanentsBy(
            String username, String email,
            String specialist,
            String bloodGroup, String mobile, String gender, Pageable pageable
    ) {
        return permanentRepository.findPermanentsBy(username, email, bloodGroup,bloodGroup, mobile, gender,
                pageable);
    }

    @Override
    public Optional<Permanent> findPermanentsByPermanentIdAndRoles(Long id, EnumRole roles) {
        return permanentRepository.findPermanentsByPermanentIdAndRoles(id , roles);
    }

    @Override
    public Optional<Permanent> getPermanentByToken(String token) {
        return Optional.empty();
                //permanentRepository.findPermanentByTokens(token);
    }

    @Override
    public Long countAllItems() {
        return permanentRepository.count();
    }


}
