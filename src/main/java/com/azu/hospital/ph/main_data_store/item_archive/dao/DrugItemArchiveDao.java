package com.azu.hospital.ph.main_data_store.item_archive.dao;

import com.azu.hospital.ph.main_data_store.item_archive.entity.DrugItemArchive;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DrugItemArchiveDao {

    Optional<DrugItemArchive> findByBarcode(String barcode);

    void addDrugToArchives(DrugItemArchive request);

}
