package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dao;

import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MedicinesRequestsListRepository extends JpaRepository<MedicinesRequestsList, Long> {

    @Query("SELECT  mrl FROM MedicinesRequestsList  mrl WHERE " +
            "(:patientName IS NULL OR mrl.patient.patientData.fullName LIKE concat('%', lower(:patientName), '%'))" +
            "ORDER BY COALESCE(mrl.updateAt, mrl.createAt) ")
    Page<MedicinesRequestsList> getAllByPatientPatientName(String patientName, Pageable pageable);


    @Query("SELECT  mrl FROM MedicinesRequestsList  mrl " +
            "ORDER BY COALESCE(mrl.updateAt, mrl.createAt) ")
    Page<MedicinesRequestsList> getMedicinesRequestsListAccordingToUpdatedAt(Pageable pageable);

    @Query("SELECT  mrl FROM MedicinesRequestsList  mrl WHERE mrl.patient.id = :patientId " +
            "ORDER BY COALESCE(mrl.updateAt, mrl.createAt) ")
    Page<MedicinesRequestsList> getAllByPatientId(Long patientId, Pageable pageable);

}
