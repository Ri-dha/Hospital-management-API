package com.azu.hospital.newPh.MedicinsRequests.dao;

import com.azu.hospital.newPh.MedicinsRequests.entity.MedicinesRequests;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicinesRequestsRepository extends JpaRepository<MedicinesRequests, Long> {

    @Query("SELECT m FROM MedicinesRequests m WHERE m.requestStatus =: status ORDER BY COALESCE(m.updateAt, m.createdAt) ")
    Page<MedicinesRequests> getAllMedicinesRequestsByStatusAndLastUpdateAsd(UnitInventoryRequestEnum status, Pageable pageable);

}
