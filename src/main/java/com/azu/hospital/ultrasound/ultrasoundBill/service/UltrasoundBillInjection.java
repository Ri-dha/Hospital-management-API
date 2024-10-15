package com.azu.hospital.ultrasound.ultrasoundBill.service;

import com.azu.hospital.ultrasound.ultrasoundBill.requests.UltrasoundBillCreateRequest;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UltrasoundBillInjection {


    private final UltrasoundBillService ultrasoundBillService;

    @Autowired
    public UltrasoundBillInjection(@Qualifier("ultrasoundBillService") UltrasoundBillService ultrasoundBillService) {

        this.ultrasoundBillService = ultrasoundBillService;
    }


    public void LoadUltrasoundBill() throws Exception {
        if (!ultrasoundBillService.getAllUltrasoundBill().isEmpty()) {
            throw new Exception("Ultrasound Bill is already loaded");
        }
        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Transvaginal
                )
        );
        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.ColorDoppler
                )
        );


        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Breast
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.ThreeDimensional
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Thyroid
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Abdominal
                )
        );


        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Vascular
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Renal
                )
        );
        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Pelvic
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Liver
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Transabdominal
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Obstetric
                )
        );


        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Transrectal
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.CarotidAndAbdominalAorta
                )
        );


        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Carotid
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.FourDimensional
                )
        );
        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.DuplexDoppler
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Scrotal
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Musculoskeletal
                )
        );

        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Echocardiogram
                )
        );


        ultrasoundBillService.createUltrasoundBill(
                new UltrasoundBillCreateRequest(
                        null,
                        null,
                        UltrasoundTypeEnum.Sonogram
                )
        );


    }


}
