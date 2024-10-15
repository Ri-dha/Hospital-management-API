package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.service.IPatientOtherConsumablesResultTableService;
import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao.OtherConsumablesDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto.OtherConsumablesDto;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto.OtherConsumablesDtoMapper;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.request.OtherConsumablesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherConsumablesService {
    private final OtherConsumablesDao otherConsumablesDao;

    private final PatientDao patientDao;

    private final OtherConsumablesDtoMapper mapper;
    private final IPatientOtherConsumablesResultTableService iPatientOtherConsumablesResultTableService;

    @Autowired
    public OtherConsumablesService(OtherConsumablesDao otherConsumablesDao, PatientDao patientDao, OtherConsumablesDtoMapper mapper, IPatientOtherConsumablesResultTableService iPatientOtherConsumablesResultTableService) {
        this.otherConsumablesDao = otherConsumablesDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.iPatientOtherConsumablesResultTableService = iPatientOtherConsumablesResultTableService;
    }


    public void updateOtherConsumables(List<OtherConsumablesRequest> request) {

        for (OtherConsumablesRequest update : request) {
          OtherConsumables otherConsumables = otherConsumablesDao.getOtherConsumablesById(update.id())
                        .orElseThrow(
                                () -> new IllegalStateException("OtherConsumables with id " + update.id() + " does not exists")
                        );
                boolean changes = false;
                if (update.name() != null && !update.name().equals(otherConsumables.getName())) {
                    otherConsumables.setName(update.name());
                    changes = true;
                }
                if (update.count() != null && !update.count().equals(otherConsumables.getCount())) {
                    otherConsumables.setCount(update.count());
                    changes = true;
                }
                if (update.note() != null && !update.note().equals(otherConsumables.getNote())) {
                    otherConsumables.setNote(update.note());
                    changes = true;
                }
                if(update.price() != null && !update.price().equals(otherConsumables.getPrice())){
                    otherConsumables.setPrice(update.price());
                    changes = true;
                }
                if (!changes) {
                    throw new RequestValidationException("No Data changes found");
                }
                otherConsumablesDao.updateOtherConsumables(otherConsumables);

        }

    }


    public OtherConsumablesDto getOtherConsumablesById(Long id) {
        OtherConsumables otherConsumables = otherConsumablesDao.getOtherConsumablesById(id)
                .orElseThrow(
                        () -> new IllegalStateException("OtherConsumables with id " + id + " does not exists")
                );

        return mapper.apply(otherConsumables);

    }

    public List<OtherConsumablesDto> getAllOtherConsumables(Long patientId) {
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new IllegalStateException("Patient with id " + patientId + " does not exists")
        );
        List<OtherConsumables> otherConsumables = otherConsumablesDao.getAllOtherConsumables(patientId);
        return otherConsumables.stream()
                .map(mapper)
                .toList();
    }

}
