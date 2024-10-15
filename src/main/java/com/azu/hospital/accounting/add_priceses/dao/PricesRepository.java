package com.azu.hospital.accounting.add_priceses.dao;

import com.azu.hospital.accounting.add_priceses.entity.Prices;
import com.azu.hospital.utils.enums.EnumItemType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface PricesRepository<T> extends JpaRepository<Prices, Long> {


    @Query("SELECT p FROM Prices p WHERE (:type IS NULL OR p.type = :type) AND (:name IS NULL OR LOWER(p.itemName) LIKE LOWER(CONCAT('%', :name, '%'))) ORDER BY COALESCE(p.updatedAt, p.createdAt) DESC")
    List<Prices> findAllByTypeAndItemName(EnumItemType type, String name, Pageable pageable);



}
