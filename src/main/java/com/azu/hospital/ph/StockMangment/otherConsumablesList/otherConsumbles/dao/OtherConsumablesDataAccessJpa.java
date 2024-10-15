package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao;


import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("otherConsumablesJpa")
public class OtherConsumablesDataAccessJpa implements OtherConsumablesDao{
    private final OtherConsumablesRepository repository;

    public OtherConsumablesDataAccessJpa(OtherConsumablesRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createList(List<OtherConsumables> otherConsumablesList) {
         repository.saveAll(new ArrayList<>(otherConsumablesList));
    }

    @Override
    public void updateOtherConsumables(OtherConsumables otherConsumables) {
        repository.save(otherConsumables);
    }

    @Override
    public Optional<OtherConsumables> getOtherConsumablesById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<OtherConsumables> getAllOtherConsumables(Long patientId) {
        return repository.getAllByPatientId(patientId);
    }

    @Override
    public List<BillsDtoSum<String>> getAllOtherConsumablesWithSum(Long patientId) {
        return repository.getAllWithSum(patientId);
    }
}
