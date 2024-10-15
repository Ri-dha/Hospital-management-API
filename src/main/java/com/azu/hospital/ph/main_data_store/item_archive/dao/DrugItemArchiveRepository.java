package com.azu.hospital.ph.main_data_store.item_archive.dao;

import com.azu.hospital.ph.main_data_store.item_archive.entity.DrugItemArchive;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface DrugItemArchiveRepository extends JpaRepository<DrugItemArchive, Long> {

    @Query("SELECT b FROM DrugItemArchive b WHERE b.barcode =:barcode")
    Optional<DrugItemArchive>findByBarcode(@Param("barcode") String barcode);
}
