package com.azu.hospital.newPh.MedicinsRequests.service;


import com.azu.hospital.newPh.Medicins.dao.MedicinesDao;
import com.azu.hospital.newPh.Medicins.entity.Medicines;
import com.azu.hospital.newPh.MedicinsRequests.dao.MedicinesRequestsDao;
import com.azu.hospital.newPh.MedicinsRequests.dto.MedicinesRequestsDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class MedicinesRequestsService {

    private final MedicinesRequestsDao medicinesRequestsDao;

    private final MedicinesRequestsDtoMapper medicinesRequestsDtoMapper;

    private final MedicinesDao medicinesDao;

    public MedicinesRequestsService(MedicinesRequestsDao medicinesRequestsDao, MedicinesRequestsDtoMapper medicinesRequestsDtoMapper, MedicinesDao medicinesDao) {
        this.medicinesRequestsDao = medicinesRequestsDao;
        this.medicinesRequestsDtoMapper = medicinesRequestsDtoMapper;
        this.medicinesDao = medicinesDao;
    }










}
