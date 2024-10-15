package com.azu.hospital.radiology.radiology_bill_handler.services;

import com.azu.hospital.radiology.radiology_bill_handler.requests.RadiologyCreateRequest;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InjectionRadiologyBill {

    private final RadiologyBillService radiologyBillService;


    @Autowired
    public InjectionRadiologyBill(@Qualifier("radiologyBillService") RadiologyBillService radiologyBillService) {
        this.radiologyBillService = radiologyBillService;
    }

    public void LoadRadiologyBill() throws Exception {
        if (!radiologyBillService.getAllRadiologyBill().isEmpty()) {
            throw new Exception("Radiology Bill is already loaded");
        }
        radiologyBillService.createRadiologyBill(
                new RadiologyCreateRequest(
                        null,
                        BigDecimal.ZERO,
                        RadiologyTypeEnum.XRay
                )
        );
        radiologyBillService.createRadiologyBill(
                new RadiologyCreateRequest(
                        null,
                        BigDecimal.ZERO,
                        RadiologyTypeEnum.XRayWithDye
                )
        );
        radiologyBillService.createRadiologyBill(
                new RadiologyCreateRequest(
                        null,
                        BigDecimal.ZERO,
                        RadiologyTypeEnum.MRI
                )
        );
        radiologyBillService.createRadiologyBill(
                new RadiologyCreateRequest(
                        null,
                        BigDecimal.ZERO,
                        RadiologyTypeEnum.MRIWithDye
                )
        );
        radiologyBillService.createRadiologyBill(
                new RadiologyCreateRequest(
                        null,
                        BigDecimal.ZERO,
                        RadiologyTypeEnum.CT_Scan
                )
        );
        radiologyBillService.createRadiologyBill(
                new RadiologyCreateRequest(
                        null,
                        BigDecimal.ZERO,
                        RadiologyTypeEnum.CT_ScanWithDye
                )
        );


    }
}