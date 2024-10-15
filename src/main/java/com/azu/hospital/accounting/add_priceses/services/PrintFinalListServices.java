package com.azu.hospital.accounting.add_priceses.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.Patient.dto.PatientDtoMapper;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.add_priceses.request.BillScreenDto;
import com.azu.hospital.accounting.all_item_expanse.Labs.dao.PatientLabExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.consumables.dao.PatientExpansesExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.drugs.dao.PatientDrugsExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.ecg.dao.PatientEcgExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.opreation.dao.PatientOperationExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao.PatientOtherConsumablesResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.radiology.dao.PatientRadiologyExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.dao.PatientUltrasoundExpanseResultTableDao;
import com.azu.hospital.accounting.patient_wallet.dao.PatientWalletDao;
import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import com.azu.hospital.ecg.internal.dao.EcgDao;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.patient_expances.dao.PatientExpanseDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao.OtherConsumablesDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.utils.enums.StockBillState;
import com.azu.hospital.utils.enums.operation.OperationStates;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("PrintFinalListServicesImp")
public class PrintFinalListServices implements IPrintFinalListServices {

    private final DrugRequestHandlerDao drugRequestHandlerDao;

    private final OperationDao operationDao;
    private final PatientExpanseDao patientExpanseDao;

    private final InternalLabDao internalLabDao;
    private final EcgDao ecgDao;

    private final InternalRadiologyTestDao internalRadiologyTestDao;

    private final InternalUltrasoundTestDao internalUltrasoundTestDao;

    private final OtherConsumablesDao otherConsumablesDao;


    private final PatientDtoMapper patientMapper;

    private final PatientDao patientDao;

    private final PatientWalletDao patientWalletDao;

    private final PatientExpansesExpanseResultTableDao patientExpansesExpanseResultTableDao;

    private final PatientDrugsExpanseResultTableDao patientDrugsExpanseResultTableDao;

    private final PatientEcgExpanseResultTableDao patientEcgExpanseResultTableDao;

    private final PatientLabExpanseResultTableDao patientLabExpanseResultTableDao;

    private final PatientOperationExpanseResultTableDao patientOperationExpanseResultTableDao;

    private final PatientRadiologyExpanseResultTableDao patientRadiologyExpanseResultTableDao;

    private final PatientUltrasoundExpanseResultTableDao patientUltrasoundExpanseResultTableDao;

    private final PatientOtherConsumablesResultTableDao patientOtherConsumablesResultTableDao;




    @Autowired
    public PrintFinalListServices(
            @Qualifier("DrugRequestHandlerJpa") DrugRequestHandlerDao drugRequestHandlerDao,
            @Qualifier("OperationJpa") OperationDao operationDao,
            @Qualifier("PatientExpanseJpa") PatientExpanseDao patientExpanseDao,
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("ecgJpa") EcgDao ecgDao,
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao,
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao,
            @Qualifier("patientDtoMapper") PatientDtoMapper patientMapper,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("PatientWalletDataJpa") PatientWalletDao patientWalletDao,
            @Qualifier("PatientExpansesExpanseResultTableJpa") PatientExpansesExpanseResultTableDao patientExpansesExpanseResultTableDao,
            @Qualifier("PatientDrugsExpanseResultTableJpa") PatientDrugsExpanseResultTableDao patientDrugsExpanseResultTableDao,
            @Qualifier("PatientEcgExpanseResultTableJpa") PatientEcgExpanseResultTableDao patientEcgExpanseResultTableDao,
            @Qualifier("PatientLabExpanseResultTableJpa") PatientLabExpanseResultTableDao patientLabExpanseResultTableDao,
            @Qualifier("PatientOperationExpanseResultTableJpa") PatientOperationExpanseResultTableDao patientOperationExpanseResultTableDao,
            @Qualifier("PatientRadiologyExpanseResultTableJpa") PatientRadiologyExpanseResultTableDao patientRadiologyExpanseResultTableDao,
            @Qualifier("PatientUltrasoundExpanseResultTableJpa") PatientUltrasoundExpanseResultTableDao patientUltrasoundExpanseResultTableDao, OtherConsumablesDao otherConsumablesDao, PatientOtherConsumablesResultTableDao patientOtherConsumablesResultTableDao
    ) {
        this.drugRequestHandlerDao = drugRequestHandlerDao;
        this.operationDao = operationDao;
        this.patientExpanseDao = patientExpanseDao;
        this.internalLabDao = internalLabDao;
        this.ecgDao = ecgDao;
        this.internalRadiologyTestDao = internalRadiologyTestDao;
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
        this.patientMapper = patientMapper;
        this.patientDao = patientDao;
        this.patientWalletDao = patientWalletDao;
        this.patientExpansesExpanseResultTableDao = patientExpansesExpanseResultTableDao;
        this.patientDrugsExpanseResultTableDao = patientDrugsExpanseResultTableDao;
        this.patientEcgExpanseResultTableDao = patientEcgExpanseResultTableDao;
        this.patientLabExpanseResultTableDao = patientLabExpanseResultTableDao;
        this.patientOperationExpanseResultTableDao = patientOperationExpanseResultTableDao;
        this.patientRadiologyExpanseResultTableDao = patientRadiologyExpanseResultTableDao;
        this.patientUltrasoundExpanseResultTableDao = patientUltrasoundExpanseResultTableDao;
        this.otherConsumablesDao = otherConsumablesDao;
        this.patientOtherConsumablesResultTableDao = patientOtherConsumablesResultTableDao;
    }


    @Override
    public List<BillsDtoSum<String>> getFinalPatientBills(Long patientId) {
        return drugRequestHandlerDao.sumAllPatientQuantityDrugsRequestByPatientId(patientId);
    }

    @Override
    public List<BillsDtoSum<RadiologyTypeEnum>> getFinalPatientBillsRadiology(Long patientId) {
        return internalRadiologyTestDao.sumAllDrugsItemQuantityForSamePatientId(patientId);
    }


    @Override
    public List<BillsDtoSum<UltrasoundTypeEnum>> getFinalPatientBillsUltrasound(Long patientId) {
        return internalUltrasoundTestDao.getInternalUltrasoundSum(patientId);
    }


    @Override
    public List<BillsDtoSum<String>> getFinalPatientBillsLab(Long patientId) {
        return internalLabDao.getAllInternalLabWithSum(patientId);
    }

    @Override
    public List<BillsDtoSum<String>> getFinalPatientBillsOtherConsumables(Long patientId) {
        return otherConsumablesDao.getAllOtherConsumablesWithSum(patientId);
    }



    @Override
    public BillScreenDto getFinalPatientBill(Long patientId) {

//        Patient patient = patientDao.getPatientById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));
//
//        List<BillsDtoSum<String>> drugs = patientDrugsExpanseResultTableDao.sumDrugQuantityPatientId(patientId);
//
//        List<BillsDtoSum<RadiologyTypeEnum>> radiology = patientRadiologyExpanseResultTableDao.sumRadiologyQuantityPatientId(patientId);
//
//        List<BillsDtoSum<UltrasoundTypeEnum>> ultrasound = patientUltrasoundExpanseResultTableDao.sumUltrasoundQuantityPatientId(patientId);
//
//        List<BillsDtoSum<String>> lab = patientLabExpanseResultTableDao.sumLabQuantityPatientId(patientId);
//
//        List<BillsDtoSum<String>> operation = patientOperationExpanseResultTableDao.sumOperationQuantityPatientId(patientId);
//
////        List<BillsDtoSum<String>> ecg = ecgDao.findSumByPatientId(patientId);
//
//        List<BillsDtoSum<String>> patientExpanse = patientExpansesExpanseResultTableDao.sumExpansesQuantityPatientId(patientId);
//
//        List<BillsDtoSum<String>> otherConsumables = otherConsumablesDao.getAllOtherConsumablesWithSum(patientId);
//
//        List<BillScreenDataDto> data = new ArrayList<>();
//
//        data.add(new BillScreenDataDto<String>("Drugs", drugs));
//        data.add(new BillScreenDataDto<String>("Lab", lab));
//        data.add(new BillScreenDataDto<RadiologyTypeEnum>("Radiology", radiology));
//        data.add(new BillScreenDataDto<UltrasoundTypeEnum>("Ultrasound", ultrasound));
//        data.add(new BillScreenDataDto<String>("Operation", operation));
////        data.add(new BillScreenDataDto<String>("Ecg", ecg));
//        data.add(new BillScreenDataDto<String>("Expanse", patientExpanse));
//        data.add(new BillScreenDataDto<String>("Other Consumables", otherConsumables));

        return null;


    }

    public List<BillsDtoSum<String>> getFinalPatientBillsOperation(Long patientId) {
        return operationDao.getAllWithSum(patientId, List.of(
                OperationStates.PatientReceived,
                OperationStates.BeforeRecover,
                OperationStates.Recovery,
                OperationStates.SentToWard,
                OperationStates.WaitingForWard));
    }


    @Override
    @Transactional
    public void payButtonProcess(Patient patient, PatientWallet patientWallet) {

        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            patientExpansesExpanseResultTableDao.getAllPatientExpansesExpanseResultTableByPatientId(patient.getId(),
                    StockBillState.UNPAID).forEach(
                    patientExpansesResultTable -> {
                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientExpansesResultTable.getTotalPrice()));
                        patientExpansesResultTable.setState(StockBillState.PAID);
                        patientExpansesResultTable.setIsArchived(true);
                        patientExpansesExpanseResultTableDao.updatePatientExpansesExpanseResultTable(patientExpansesResultTable);
                    }
            );
        }


//        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {
//            patientDrugsExpanseResultTableDao.getAllPatientDrugsExpanseResultTableByPatientId(patient.getId(),
//                    StockBillState.UNPAID).forEach(
//                    patientDrugsResultTable -> {
//                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientDrugsResultTable.getTotalPricesForOneDrug()));
//                        patientDrugsResultTable.setState(StockBillState.PAID);
//                        patientDrugsExpanseResultTableDao.updatePatientDrugsExpanseResultTable(patientDrugsResultTable);
//                    }
//            );
//        }


        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {

            patientEcgExpanseResultTableDao.getAllPatientEcgExpanseResultTableByPatientId(patient.getId(),
                    StockBillState.UNPAID).forEach(
                    patientEcgResultTable -> {
                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientEcgResultTable.getTotalEcgPrice()));
                        patientEcgResultTable.setState(StockBillState.PAID);
                        patientEcgExpanseResultTableDao.updatePatientEcgExpanseResultTable(patientEcgResultTable);
                    }
            );
        }

        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {

            patientLabExpanseResultTableDao.getAllPatientLabExpanseResultTableByPatientId(patient.getId(),
                    StockBillState.UNPAID).forEach(
                    patientLabResultTable -> {
                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientLabResultTable.getTotalLabTestPrice()));
                        patientLabResultTable.setState(StockBillState.PAID);
                        patientLabExpanseResultTableDao.updatePatientLabExpanseResultTable(patientLabResultTable);
                    }
            );

        }

        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            patientOperationExpanseResultTableDao.getAllPatientOperationExpanseResultTableByPatientId(patient.getId(),
                    StockBillState.UNPAID).forEach(
                    patientOperationResultTable -> {
                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientOperationResultTable.getTotalOperationPrice()));
                        patientOperationResultTable.setState(StockBillState.PAID);
                        patientOperationExpanseResultTableDao.updatePatientOperationExpanseResultTable(patientOperationResultTable);
                    }
            );
        }

        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {

            patientRadiologyExpanseResultTableDao.getAllPatientRadiologyExpanseResultTableByPatientId(patient.getId(),
                    StockBillState.UNPAID).forEach(
                    patientRadiologyResultTable -> {
                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientRadiologyResultTable.getTotalRadiologyPrice()));
                        patientRadiologyResultTable.setState(StockBillState.PAID);
                        patientRadiologyExpanseResultTableDao.updatePatientRadiologyExpanseResultTable(patientRadiologyResultTable);
                    }
            );

        }

        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            patientUltrasoundExpanseResultTableDao.getAllPatientUltrasoundExpanseResultTableByPatientId(patient.getId(),
                    StockBillState.UNPAID).forEach(
                    patientUltrasoundResultTable -> {
                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientUltrasoundResultTable.getTotalUltrasoundPrice()));
                        patientUltrasoundResultTable.setState(StockBillState.PAID);
                        patientUltrasoundExpanseResultTableDao.updatePatientUltrasoundExpanseResultTable(patientUltrasoundResultTable);
                    }
            );
        }
        if (patientWallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            patientOtherConsumablesResultTableDao.getAllPatientOtherConsumablesResultTableByPatientId(patient.getId(),
                    StockBillState.UNPAID).forEach(
                    patientOtherConsumablesResultTable -> {
                        patientWallet.setBalance(patientWallet.getBalance().subtract(patientOtherConsumablesResultTable.getTotalPrice()));
                        patientOtherConsumablesResultTable.setState(StockBillState.PAID);
                        patientOtherConsumablesResultTableDao.updatePatientOtherConsumablesResultTable(patientOtherConsumablesResultTable);
                    }
            );

        }


        patientWalletDao.updatePatientWallet(patientWallet);
    }
}

