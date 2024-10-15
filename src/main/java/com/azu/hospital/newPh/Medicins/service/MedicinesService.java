package com.azu.hospital.newPh.Medicins.service;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.newPh.Medicins.Dto.MedicinesDto;
import com.azu.hospital.newPh.Medicins.Dto.MedicinesDtoMapper;
import com.azu.hospital.newPh.Medicins.Dto.MedicinesDtoNameMapper;
import com.azu.hospital.newPh.Medicins.dao.MedicinesDao;
import com.azu.hospital.newPh.Medicins.entity.Medicines;
import com.azu.hospital.newPh.Medicins.requests.MedicinesCreateRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinesService {

    private final MedicinesDao medicinesDao;

    private final MedicinesDtoMapper medicinesDtoMapper;

    private final MedicinesDtoNameMapper medicinesDtoNameMapper;

    @Autowired
    public MedicinesService(MedicinesDao medicinesDao, MedicinesDtoMapper medicinesDtoMapper, MedicinesDtoNameMapper medicinesDtoNameMapper) {
        this.medicinesDao = medicinesDao;
        this.medicinesDtoMapper = medicinesDtoMapper;
        this.medicinesDtoNameMapper = medicinesDtoNameMapper;
    }


    public void createMedicine(MedicinesCreateRecord request) {
        Medicines medicines = new Medicines();
        medicines.setName(request.name());
        medicines.setBarCode(request.barCode());
        medicines.setPrice(request.price());
        medicines.setQuantity(request.quantity());
        medicinesDao.insertMedicine(medicines);
    }


    public void updateMedicine(MedicinesCreateRecord request, Long id) {
        Medicines medicines = medicinesDao.getMedicineById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicine with id " + id + " does not exist")
        );
        boolean isUpdated = false;
        if (request.name() != null) {
            medicines.setName(request.name());
            isUpdated = true;
        }
        if (request.barCode() != null) {
            medicines.setBarCode(request.barCode());
            isUpdated = true;
        }
        if (request.price() != null) {
            medicines.setPrice(request.price());
            isUpdated = true;
        }
        if (request.quantity() != null) {
            medicines.setQuantity(request.quantity());
            isUpdated = true;
        }
        if (isUpdated) {
            medicinesDao.updateMedicine(medicines);
        }

    }


    public void deleteMedicine(Long id) {
        medicinesDao.deleteMedicine(id);
    }

    public Page<MedicinesDto> getAllMedicines(Pageable pageable) {
        return medicinesDao.getAllMedicines(pageable).map(medicinesDtoMapper);
    }


    public MedicinesDto getMedicineById(Long id) {
        Medicines medicines = medicinesDao.getMedicineById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicine with id " + id + " does not exist")
        );
        return medicinesDtoMapper.apply(medicines);
    }


    public List<MedicinesDto> getByName(String name, Pageable pageable) {
        return medicinesDao.getByName(name)
                .stream()
                .map(medicinesDtoNameMapper)
                .toList();
    }

    public Page<MedicinesDto> getAllByFilter(String name, String barCode, Long price, Long quantity, Pageable pageable) {
        return medicinesDao.getAllByFilter(name, barCode, price, quantity, pageable).map(medicinesDtoMapper);
    }

    public void createAll(List<MedicinesCreateRecord> request) {
        List<Medicines> medicines = request.stream()
                .map(r -> new Medicines(r.name(), r.barCode(), r.price(), r.quantity()))
                .toList();
        medicinesDao.createAll(medicines);

    }

}
