package com.azu.hospital.ph.Sales.solid_item_list.dao;

import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository("SoldItemJpa")
public class SoldItemJpaDataAccess implements SoldItemDao {

    private final SoldItemsRepository repository;

    @Autowired
    public SoldItemJpaDataAccess(SoldItemsRepository soldItemsRepository) {
        this.repository = soldItemsRepository;
    }


    @Override
    public void addItemList(List<SoldItems> items) {
        repository.saveAll(new ArrayList<>(items));
    }

    @Override
    public void updateExistItem(List<SoldItems> update) {
       repository.saveAll(update);
    }

    @Override
    public Page<SoldItems> getAllItemLists(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<SoldItems> getItemFormListById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<SoldItems> getAllItemListByBillId(Long billId) {
        return repository.findAllBySoldBillBillId(billId);
    }

    @Override
    public void deleteSolidItemById(Long id) {
        repository.deleteById(id);
    }
}
