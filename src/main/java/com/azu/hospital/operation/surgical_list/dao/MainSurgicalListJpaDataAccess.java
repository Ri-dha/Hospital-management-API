package com.azu.hospital.operation.surgical_list.dao;

import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("MainSurgicalListJpa")
public class MainSurgicalListJpaDataAccess implements MainSurgicalListDao{

    private final MainSurgicalListRepository repository;

    @Autowired
    public MainSurgicalListJpaDataAccess(MainSurgicalListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addSurgicalName(MainSurgicalList name) {
        repository.save(name);
    }

    @Override
    public void updateSurgicalName( MainSurgicalList name) {
        repository.save(name);
    }

    @Override
    public Optional<MainSurgicalList> getSurgicalById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<MainSurgicalList> getAllSurgicalList(String name, Pageable pageable) {
        return repository.findAllBySurgicalName(name, pageable);
    }

    @Override
    public Boolean existsAllBySurgicalName(String name) {
        return repository.existsAllBySurgicalName(name);
    }

    @Override
    public List<MainSurgicalList> getAllSurgicalList() {
        return repository.findAll();
    }

    @Override
    public Page<MainSurgicalList> getAllWithPrice(Pageable pageable) {
        return repository.getAllWithPrice(pageable);
    }

    @Override
    public Optional<MainSurgicalList> getSurgicalByName(String name) {
        return repository.getSurgicalByName(name);
    }
}
