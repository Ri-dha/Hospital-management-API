package com.azu.hospital.accounting.add_priceses.dao;

import com.azu.hospital.accounting.add_priceses.entity.Prices;
import com.azu.hospital.utils.enums.EnumItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PricesJpa")
public class PricesJpaDataAccess implements PricesDao{
    private final PricesRepository repository;

    @Autowired
    public PricesJpaDataAccess(PricesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addItemPrices(Prices request) {
        repository.save(request);
    }

    @Override
    public void updateItemPrices(Prices update) {
        repository.save(update);
    }

    @Override
    public void deleteItemPrices(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<Prices> getItemPriceById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Prices> getAllItemPrices(EnumItemType itemType, String name, Pageable pageable) {
        return repository.findAllByTypeAndItemName(itemType, name, pageable);
    }
}
