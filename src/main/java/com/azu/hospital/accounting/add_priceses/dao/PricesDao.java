package com.azu.hospital.accounting.add_priceses.dao;

import com.azu.hospital.accounting.add_priceses.entity.Prices;
import com.azu.hospital.utils.enums.EnumItemType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PricesDao {

    void addItemPrices(Prices request);

    void updateItemPrices(Prices update);

    void deleteItemPrices(Long id);

    Optional<Prices> getItemPriceById(Long id);

    List<Prices> getAllItemPrices(EnumItemType itemType, String name, Pageable pageable);
}
