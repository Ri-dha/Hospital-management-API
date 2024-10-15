package com.azu.hospital.radiology.radiology_bill_handler.dao;


import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("RadiologyBillJpa")
public class RadiologyBillDataJpa implements RadiologyBillDao {

    private final RadiologyBillRepository radiologyBillRepository;

    @Autowired
    public RadiologyBillDataJpa(@Qualifier("radiologyBillRepository") RadiologyBillRepository radiologyBillRepository) {
        this.radiologyBillRepository = radiologyBillRepository;
    }

    @Override
    public RadiologyBillHandler createRadiologyBill(RadiologyBillHandler radiologyBillHandler) {
        return radiologyBillRepository.save(radiologyBillHandler);
    }

    @Override
    public RadiologyBillHandler updateRadiologyBill(RadiologyBillHandler radiologyBillHandler) {
        return radiologyBillRepository.save(radiologyBillHandler);
    }

    @Override
    public List<RadiologyBillHandler> getAllRadiologyBill() {
        return radiologyBillRepository.findAll();
    }

    @Override
    public Optional<RadiologyBillHandler> getRadiologyBillById(Long id) {
        return radiologyBillRepository.findById(id);
    }

    @Override
    public RadiologyBillHandler findByType(RadiologyTypeEnum type) {
        return radiologyBillRepository.findByType(type);
    }

    @Override
    public Page<RadiologyBillHandler> getAllWithPrice(Pageable pageable) {
        return radiologyBillRepository.getAllWithPrice(pageable);
    }


}
