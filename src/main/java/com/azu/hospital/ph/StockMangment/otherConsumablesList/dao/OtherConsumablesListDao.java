package com.azu.hospital.ph.StockMangment.otherConsumablesList.dao;

import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;

import java.util.List;
import java.util.Optional;

public interface OtherConsumablesListDao {

    OtherConsumablesList addOtherConsumables(OtherConsumablesList otherConsumablesList);

    void createOtherConsumablesList(OtherConsumablesList otherConsumablesList);

    void updateOtherConsumablesList(OtherConsumablesList otherConsumablesList);

    Optional<OtherConsumablesList> getOtherConsumablesList(Long listId);

    List<OtherConsumablesList> getAllOtherConsumablesListByPatientId(Long patientId);
}
