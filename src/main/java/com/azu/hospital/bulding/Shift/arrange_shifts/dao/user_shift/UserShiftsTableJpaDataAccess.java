package com.azu.hospital.bulding.Shift.arrange_shifts.dao.user_shift;

import com.azu.hospital.bulding.Shift.arrange_shifts.entity.UserShiftsTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository("UserShiftsTableJpa")
public non-sealed class UserShiftsTableJpaDataAccess implements UserShiftsTableDao{

    private final UserShiftsTableRepository repository;

    @Autowired
    public UserShiftsTableJpaDataAccess(UserShiftsTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addListUserShiftsTable(List<UserShiftsTable> requests) {
        repository.saveAll(new ArrayList<>(requests));
    }

    @Override
    public void updateUserShiftsTable(UserShiftsTable update) {
       repository.save(update);
    }

    @Override
    public Optional<UserShiftsTable> getUserShiftsTableById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<UserShiftsTable> getAllUserShiftsTable() {
        return repository.findAll();
    }
}
