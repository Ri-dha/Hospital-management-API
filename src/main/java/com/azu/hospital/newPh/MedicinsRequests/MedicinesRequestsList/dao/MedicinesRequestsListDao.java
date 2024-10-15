package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dao;

import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MedicinesRequestsListDao {
    MedicinesRequestsList addMedicinesRequestsList(MedicinesRequestsList medicinesRequestsList);

    void updateMedicinesRequestsList(MedicinesRequestsList medicinesRequestsList);

    Optional<MedicinesRequestsList> getMedicinesRequestsListById(Long id);

    Page<MedicinesRequestsList> getMedicinesRequestsListAllWithFilter(String patientName, Pageable pageable);

    Page<MedicinesRequestsList> getMedicinesRequestsListAccordingToUpdatedAt(Pageable pageable);

    Page<MedicinesRequestsList> getAllByPatientId(Long patientId, Pageable pageable);


}
