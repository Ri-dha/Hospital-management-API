package com.azu.hospital.accounting.add_priceses.services;


import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.ecg.ecgbill.dao.EcgBillDao;
import com.azu.hospital.ecg.internal.services.EcgService;
import com.azu.hospital.radiology.internal.services.InternalRadiologyTestService;
import com.azu.hospital.radiology.radiology_bill_handler.dao.RadiologyBillDao;
import com.azu.hospital.ultrasound.internal.services.InternalUltrasoundTestService;
import com.azu.hospital.ultrasound.ultrasoundBill.dao.UltrasoundBillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadData {

    private final EcgBillDao ecgBillDao;
    private final EcgService ecgService;

    private final RadiologyBillDao radiologyBillDao;
    private final InternalRadiologyTestService internalRadiologyTestService;

    private final UltrasoundBillDao ultrasoundBillDao;
    private final InternalUltrasoundTestService internalUltrasoundTestService;
    private final PatientDao patientDao;

    @Autowired
    public LoadData(EcgBillDao ecgBillDao, EcgService ecgService, RadiologyBillDao radiologyBillDao, InternalRadiologyTestService internalRadiologyTestService, UltrasoundBillDao ultrasoundBillDao, InternalUltrasoundTestService internalUltrasoundTestService, PatientDao patientDao) {
        this.ecgBillDao = ecgBillDao;
        this.ecgService = ecgService;
        this.radiologyBillDao = radiologyBillDao;
        this.internalRadiologyTestService = internalRadiologyTestService;
        this.ultrasoundBillDao = ultrasoundBillDao;

        this.internalUltrasoundTestService = internalUltrasoundTestService;

        this.patientDao = patientDao;
    }

//    File file1 = new File("C:\\Users\\Ridha\\Pictures\\51R0Ui4.png");
//    MultipartFile multipartFile;
//
//    {
//        try {
//            multipartFile = new CustomMultipartFile(file1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void LoadDataService() throws Exception {
//
//        ecgBillDao.createEcgBill(
//                new EcgBill(
//                        "note",
//                        BigDecimal.valueOf(200.0)
//                )
//        );
//        ecgService.createNewEcgTest(
//                new InternalEcgTestRequest(
//                        1L,
//                        "note",
//                        Arrays.asList(multipartFile)
//                )
//        );
//        ecgService.createNewEcgTest(
//                new InternalEcgTestRequest(
//                        1L,
//                        "note",
//                        Arrays.asList(multipartFile)
//                )
//        );
//
//        radiologyBillDao.createRadiologyBill(
//                new RadiologyBillHandler(
//                        "note",
//                        BigDecimal.valueOf(200.0),
//                        RadiologyTypeEnum.XRay
//                )
//        );
//        radiologyBillDao.createRadiologyBill(
//                new RadiologyBillHandler(
//                        "note",
//                        BigDecimal.valueOf(50.0),
//                        RadiologyTypeEnum.XRayWithDye
//                )
//        );
//        radiologyBillDao.createRadiologyBill(
//                new RadiologyBillHandler(
//                        "note",
//                        BigDecimal.valueOf(50.0),
//                        RadiologyTypeEnum.MRI
//                )
//        );
//        radiologyBillDao.createRadiologyBill(
//                new RadiologyBillHandler(
//                        "note",
//                        BigDecimal.valueOf(50.0),
//                        RadiologyTypeEnum.MRIWithDye
//                )
//        );
//
//        internalRadiologyTestService.createNewRadiologyTest(
//                new CreateInternalRadiologyTestRequest(
//                        1L,
//                        "note",
//                        RadiologyTypeEnum.XRay
//                )
//        );
//        internalRadiologyTestService.createNewRadiologyTest(
//                new CreateInternalRadiologyTestRequest(
//                        1L,
//                        "note",
//                        RadiologyTypeEnum.XRay
//                )
//        );
//        internalRadiologyTestService.createNewRadiologyTest(
//                new CreateInternalRadiologyTestRequest(
//                        1L,
//                        "note",
//                        RadiologyTypeEnum.XRay
//                )
//        );
//        internalRadiologyTestService.createNewRadiologyTest(
//                new CreateInternalRadiologyTestRequest(
//                        1L,
//                        "note",
//                        RadiologyTypeEnum.XRayWithDye
//                )
//        );
//
//        internalRadiologyTestService.createNewRadiologyTest(
//                new CreateInternalRadiologyTestRequest(
//                        1L,
//                        "note",
//                        RadiologyTypeEnum.XRayWithDye
//                )
//        );
//        internalRadiologyTestService.createNewRadiologyTest(
//                new CreateInternalRadiologyTestRequest(
//                        1L,
//                        "note",
//                        RadiologyTypeEnum.MRI
//                )
//        );
//        internalRadiologyTestService.createNewRadiologyTest(
//                new CreateInternalRadiologyTestRequest(
//                        1L,
//                        "note",
//                        RadiologyTypeEnum.MRIWithDye
//                )
//        );
//
//        ultrasoundBillDao.createUltrasoundBill(
//                new UltrasoundBill(
//                        "note",
//                        BigDecimal.valueOf(200.0),
//                        UltrasoundTypeEnum.Transvaginal
//                )
//        );
//        ultrasoundBillDao.createUltrasoundBill(
//                new UltrasoundBill(
//                        "note",
//                        BigDecimal.valueOf(200.0),
//                        UltrasoundTypeEnum.ColorDoppler
//                )
//        );
//        ultrasoundBillDao.createUltrasoundBill(
//                new UltrasoundBill(
//                        "note",
//                        BigDecimal.valueOf(200.0),
//                        UltrasoundTypeEnum.Breast
//                )
//        );
//
//        ultrasoundBillDao.createUltrasoundBill(
//                new UltrasoundBill(
//                        "note",
//                        BigDecimal.valueOf(200.0),
//                        UltrasoundTypeEnum.ThreeDimensional
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.Transvaginal
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.Transvaginal
//                )
//        );
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.Transvaginal
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.ColorDoppler
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.Breast
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.Breast
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.ThreeDimensional
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.ThreeDimensional
//                )
//        );
//
//        internalUltrasoundTestService.createNewUltrasoundTest(
//                new CreateInternalUltrasoundTestRequest(
//                        1L,
//                        "note",
//                        UltrasoundTypeEnum.ThreeDimensional
//                )
//        );
//}


}
