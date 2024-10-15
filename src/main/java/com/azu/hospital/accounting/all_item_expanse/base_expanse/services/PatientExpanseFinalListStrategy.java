package com.azu.hospital.accounting.all_item_expanse.base_expanse.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.Patient.dto.PatientDtoMapper;
import com.azu.hospital.Patients.entryTable.dao.EntryTableDao;
import com.azu.hospital.Patients.entryTable.dto.EntryTableDateDtoMapper;
import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.FinalPatientExpensesDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.PatientExpanseDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.GetPatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.dao.SoldBillDao;
import com.azu.hospital.ph.Sales.dto.SoldBillDto;
import com.azu.hospital.ph.Sales.dto.SoldBillDtoMapper;
import com.azu.hospital.ph.Sales.dto.SoldBillDtoMapperV2;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.BillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientExpanseFinalListStrategy {

    private final List<GetPatientExpanseFinalList> expenseServices;
    private final List<PayExpanseFinalList> payExpanseFinalLists;
    private final PatientDao patientDao;
    private final EntryTableDao entryTableDao;
    private final EntryTableDateDtoMapper entryTableDateDtoMapper;
    private final PatientDtoMapper patientDtoMapper;
    private final ExceptionsMessageReturn messageReturn;
    private final SoldBillDao soldBillDao;
    private final SoldBillDtoMapperV2 soldBillDtoMapper;


    @Autowired
    public PatientExpanseFinalListStrategy(List<GetPatientExpanseFinalList> expenseServices,
                                           List<PayExpanseFinalList> payExpanseFinalLists,
                                           PatientDao patientDao,
                                           EntryTableDao entryTableDao,
                                           EntryTableDateDtoMapper entryTableDateDtoMapper,
                                           PatientDtoMapper patientDtoMapper,
                                           ExceptionsMessageReturn messageReturn,
                                           SoldBillDao soldBillDao,
                                           SoldBillDtoMapperV2 soldBillDtoMapper) {
        this.expenseServices = expenseServices;
        this.payExpanseFinalLists = payExpanseFinalLists;
        this.patientDao = patientDao;
        this.entryTableDao = entryTableDao;
        this.entryTableDateDtoMapper = entryTableDateDtoMapper;
        this.patientDtoMapper = patientDtoMapper;
        this.messageReturn = messageReturn;
        this.soldBillDao = soldBillDao;
        this.soldBillDtoMapper = soldBillDtoMapper;
    }

    public PatientExpanseDtoScreen getWithPatientData(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());


        Patient patient = patientDao.getPatientById(patientId).orElseThrow(() -> new ResourceNotFoundException(
                messages.getString("patientNotFound") + " " + patientId

        ));

        List<ExpanseDtoScreen> categorizedExpenses = getAllExpensesByPatientId(patientId);

        List<SoldBill> soldBills = soldBillDao.getAllPatientSlidBillByPatientId(patientId);
        List<SoldBillDto> soldBillDtoList = new ArrayList<>();

        for (SoldBill soldBill : soldBills) {
            soldBillDtoList.add(soldBillDtoMapper.apply(soldBill));
        }


        return new PatientExpanseDtoScreen(patientDtoMapper.toDto(patient), categorizedExpenses, soldBillDtoList);
    }


    public List<ExpanseDtoScreen> getAllExpensesByPatientId(Long patientId) {
        List<ExpanseDtoScreen> categorizedExpenses = new ArrayList<>();

        for (GetPatientExpanseFinalList service : expenseServices) {
            List<ExpanseDto> expenses = service.getPatientExpanseByPatientId(patientId).getExpanseDto();
            String categoryName = service.getPatientExpanseByPatientId(patientId).getName();
            categorizedExpenses.add(new ExpanseDtoScreen(categoryName, expenses));
        }
        return categorizedExpenses;
    }

    public void payExpanseFinalList(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(patientId).orElseThrow(() -> new ResourceNotFoundException(

                messages.getString("patientNotFound") + " " + patientId
        ));

        List<SoldBill> soldBills = soldBillDao.getAllPatientSlidBillByPatientId(patientId);

        for (SoldBill soldBill : soldBills) {
            soldBill.setArchived(true);
            soldBill.setDateOfPayment(LocalDate.now());
            soldBillDao.updateExistSoldBill(soldBill);
        }

        EntryTable entryTable = entryTableDao.getEntryTableByPatientIdAndEntryDateIsNull(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("entryTableNotFound") + " " + patientId
                ));

        for (PayExpanseFinalList payExpanseFinalList : payExpanseFinalLists) {
            payExpanseFinalList.payExpanseFinalList(patientId);
        }
        patient.setBillState(BillState.PAID);
        entryTable.setEntryDate(Instant.now());

        patientDao.updatePatient(patient);
    }


    public PatientExpanseDtoScreen getArchivedWithPatientData(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(() -> new ResourceNotFoundException(
                messages.getString("patientNotFound") + " " + patientId

        ));

        List<ExpanseDtoScreen> categorizedExpenses = getAllArchivedExpensesByPatientId(patientId);


        List<SoldBill> soldBills = soldBillDao.getAllPatientSlidBillByPatientId(patientId);
        List<SoldBillDto> soldBillDtoList = new ArrayList<>();

        for (SoldBill soldBill : soldBills) {
            soldBillDtoList.add(soldBillDtoMapper.apply(soldBill));
        }


        return new PatientExpanseDtoScreen(patientDtoMapper.toDto(patient), categorizedExpenses, soldBillDtoList);
    }


    public List<ExpanseDtoScreen> getAllArchivedExpensesByPatientId(Long patientId) {
        List<ExpanseDtoScreen> categorizedExpenses = new ArrayList<>();

        for (GetPatientExpanseFinalList service : expenseServices) {
            List<ExpanseDto> expenses = service.getArchivedPatientExpanseByPatientId(patientId).getExpanseDto();
            String categoryName = service.getArchivedPatientExpanseByPatientId(patientId).getName();
            categorizedExpenses.add(new ExpanseDtoScreen(categoryName, expenses));
        }
        return categorizedExpenses;
    }


    public FinalPatientExpensesDtoScreen getWithPatientDataAndDate(Long patientId, Long EntryTableId, LocalDate date) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(() -> new ResourceNotFoundException(
                messages.getString("patientNotFound") + " " + patientId

        ));
        EntryTable entryTable = entryTableDao.getEntryTableById(EntryTableId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("entryTableNotFound") + " " + EntryTableId
                ));

        List<ExpanseDtoScreen> categorizedExpenses = getExpensesByPatientIdAndDate(patientId, date);

        List<SoldBill> soldBills = soldBillDao.getAllPatientSoldBillByPatientIdAndIsArchivedIsTrueAndDateOfPayment(patientId, date);
        List<SoldBillDto> soldBillDtoList = new ArrayList<>();
        for (SoldBill soldBill : soldBills) {
            soldBillDtoList.add(soldBillDtoMapper.apply(soldBill));
        }


        return new FinalPatientExpensesDtoScreen(patientDtoMapper.toDto(patient), categorizedExpenses, soldBillDtoList, entryTableDateDtoMapper.apply(entryTable));
    }


    public List<ExpanseDtoScreen> getExpensesByPatientIdAndDate(Long patientId, LocalDate date) {
        List<ExpanseDtoScreen> categorizedExpenses = new ArrayList<>();

        for (GetPatientExpanseFinalList service : expenseServices) {
            List<ExpanseDto> expenses = service.GetPatientExpanseFinalListByIdAndDate(patientId, date).getExpanseDto();
            String categoryName = service.GetPatientExpanseFinalListByIdAndDate(patientId, date).getName();
            categorizedExpenses.add(new ExpanseDtoScreen(categoryName, expenses));
        }
        return categorizedExpenses;
    }


}
