package com.azu.hospital.ph.StockMangment.otherConsumablesList.dao;


import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("otherConsumablesListJpa")
public class OtherConsumablesListDataAccessJpa implements OtherConsumablesListDao {
    private final OtherConsumablesListRepository repository;

    public OtherConsumablesListDataAccessJpa(OtherConsumablesListRepository repository) {
        this.repository = repository;
    }


    @Override
    public OtherConsumablesList addOtherConsumables(OtherConsumablesList otherConsumablesList) {
        repository.save(otherConsumablesList);
        return otherConsumablesList;
    }

    @Override
    public void createOtherConsumablesList(OtherConsumablesList otherConsumablesList) {
        repository.save(otherConsumablesList);
    }

    @Override
    public void updateOtherConsumablesList(OtherConsumablesList otherConsumablesList) {
        repository.save(otherConsumablesList);
    }

    @Override
    public Optional<OtherConsumablesList> getOtherConsumablesList(Long listId) {
        return repository.findById(listId);
    }

    @Override
    public List<OtherConsumablesList> getAllOtherConsumablesListByPatientId(Long patientId) {
        return repository.getAllByPatientId(patientId);
    }
}
