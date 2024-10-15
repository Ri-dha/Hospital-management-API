package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao;


import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;

import java.util.List;
import java.util.Optional;

public interface OtherConsumablesDao {


    void createList(List<OtherConsumables> otherConsumablesList);
    void updateOtherConsumables(OtherConsumables otherConsumables);

    Optional<OtherConsumables> getOtherConsumablesById(Long id);

    List<OtherConsumables> getAllOtherConsumables(Long patientId);

    List<BillsDtoSum<String>> getAllOtherConsumablesWithSum(Long patientId);

}
