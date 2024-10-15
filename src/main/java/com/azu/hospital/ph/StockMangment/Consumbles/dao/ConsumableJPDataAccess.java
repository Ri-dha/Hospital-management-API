package com.azu.hospital.ph.StockMangment.Consumbles.dao;

import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("consumableJpa")
@Qualifier("consumableJpa")
public class ConsumableJPDataAccess implements ConsumableDao {

    private final ConsumableRepository consumableRepository;

    public ConsumableJPDataAccess(ConsumableRepository consumableRepository) {
        this.consumableRepository = consumableRepository;
    }


    @Override
    public Page<Consumables> selectAllConsumables(String consumableName, String barcode, Pageable pageable) {
        return consumableRepository.selectAllConsumables(consumableName, barcode, pageable);
    }

    @Override
    public Optional<Consumables> selectConsumableById(Long consumableId) {
        return consumableRepository.findById(consumableId);
    }

    @Override
    public Optional<Consumables> findConsumableById(Long consumableId) {
        return consumableRepository.findById(consumableId);
    }
    @Override
    public Consumables insertConsumable(Consumables consumables) {
        return consumableRepository.save(consumables);
    }

    @Override
    public void updateConsumable(Consumables update) {
       consumableRepository.save(update);
    }

    @Override
    public void deleteConsumableById(Long consumableId) {
      consumableRepository.deleteById(consumableId);
    }

    @Override
    public boolean consumableExistById(Long consumableId) {
        return consumableRepository.existsById(consumableId);
    }



    @Override
    public List<Consumables> getConsumablesByBillId(Long consumableId) {
        return consumableRepository.getConsumablesByBillId(consumableId);
    }


    @Override
    public Optional<Consumables> findConsumablesByBarcode(String barcode) {
        return consumableRepository.findConsumablesByBarcode(barcode);
    }

    @Override
    public Optional<Consumables> findConsumablesByConsumableNameContainingIgnoreCase(String consumableName) {
        return consumableRepository.findConsumablesByConsumableNameContainingIgnoreCase(consumableName);
    }
}
