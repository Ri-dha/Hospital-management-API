package com.azu.hospital.radiology.radiology_bill_handler.dao;


import com.azu.hospital.radiology.radiology_bill_handler.dto.RadiologyBillDto;
import com.azu.hospital.radiology.radiology_bill_handler.entity.RadiologyBillHandler;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RadiologyBillDao {

    RadiologyBillHandler createRadiologyBill(RadiologyBillHandler radiologyBillHandler);

    RadiologyBillHandler updateRadiologyBill(RadiologyBillHandler radiologyBillHandler);

    List<RadiologyBillHandler> getAllRadiologyBill();

    Optional<RadiologyBillHandler> getRadiologyBillById(Long id);

    RadiologyBillHandler findByType(RadiologyTypeEnum type);

    Page<RadiologyBillHandler> getAllWithPrice(Pageable pageable);
}
