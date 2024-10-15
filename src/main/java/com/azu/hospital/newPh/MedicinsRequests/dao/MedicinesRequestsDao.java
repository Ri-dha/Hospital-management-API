package com.azu.hospital.newPh.MedicinsRequests.dao;

import com.azu.hospital.newPh.MedicinsRequests.entity.MedicinesRequests;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MedicinesRequestsDao {

    List<MedicinesRequests> createMedicinesRequests(List<MedicinesRequests> medicinesRequests);

    void updateMedicinesRequests(MedicinesRequests update);

    void deleteMedicinesRequestsById(Long id);

    Optional<MedicinesRequests> getMedicinesRequestsById(Long id);

    Page<MedicinesRequests> getAllMedicinesRequests(Pageable pageable);

    Page<MedicinesRequests> getAllMedicinesRequestsByStatusAndLastUpdateAsd(UnitInventoryRequestEnum status, Pageable pageable);

}
