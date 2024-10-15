package com.azu.hospital.accounting.patient_new_wallet.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.hospitalaccounting.services.HospitalAccountingAddService;
import com.azu.hospital.accounting.patient_new_wallet.PatientNewWallet;
import com.azu.hospital.accounting.patient_new_wallet.dao.PatientNewWalletDao;
import com.azu.hospital.accounting.patient_new_wallet.request.PatientNewWalletRegistrationsRequest;
import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dao.CustomSalaryDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao.MainSalaryTableDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientNewWalletAddServices {


    private final PatientNewWalletDao patientNewWalletDao;
    private final DoctorDao doctorDao;
    private final MainSurgicalListDao mainSurgicalListDao;
    private final CustomSalaryDao customSalaryDao;
    private final ExceptionsMessageReturn messageReturn;
    private final PatientDao patientDao;

    private final MainSalaryTableDao mainSalaryTableDao;

    private final HospitalAccountingAddService hospitalAccountingAddService;

    @Autowired
    public PatientNewWalletAddServices(PatientNewWalletDao patientNewWalletDao,
                                       DoctorDao doctorDao,
                                       MainSurgicalListDao mainSurgicalListDao,
                                       CustomSalaryDao customSalaryDao,
                                       ExceptionsMessageReturn messageReturn,
                                       PatientDao patientDao, MainSalaryTableDao mainSalaryTableDao, HospitalAccountingAddService hospitalAccountingAddService) {
        this.patientNewWalletDao = patientNewWalletDao;
        this.doctorDao = doctorDao;
        this.mainSurgicalListDao = mainSurgicalListDao;
        this.customSalaryDao = customSalaryDao;
        this.messageReturn = messageReturn;
        this.patientDao = patientDao;
        this.mainSalaryTableDao = mainSalaryTableDao;
        this.hospitalAccountingAddService = hospitalAccountingAddService;
    }


    @Transactional
    public void insertPatientNewWallet(PatientNewWalletRegistrationsRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = getPatient(request, messages);
        MainSurgicalList mainSurgicalList = extractedMainSurgicalList(request, messages);
        Doctor doctor = patient.getDoctorSpecials().getDoctor();
        createNewPatientWallet(request);
        insertToCustomSalaryForDoctors(request, doctor, patient);
        thisMethodForCalculateHospitalIncome(request, patient, mainSurgicalList);
    }


    private void thisMethodForCalculateHospitalIncome(PatientNewWalletRegistrationsRequest request, Patient patient, MainSurgicalList mainSurgicalList) {
        hospitalAccountingAddService.addNewHospitalAccounting(
                request.operationName(),
                request.operationId(),
                request.patientId(),
                request.operationCost(),
                mainSurgicalList.getHospitalPercentage(),
                patient.getPatientData().getFullName()

        );
    }

    private void createNewPatientWallet(PatientNewWalletRegistrationsRequest request) {
        Patient pa= getPatient(request, ResourceBundle.getBundle("messages"));


        PatientNewWallet newWallet = new PatientNewWallet(
                request.operationId(),
                pa.getDoctorSpecials().getDoctor().getId(),
                pa.getId(),
                pa.getPatientData().getFullName(),
                request.operationName(),
                pa.getDoctorSpecials().getDoctor().getUsernameSpecial(),
                request.note(),
                request.operationCost(),
                request.doctorPercentage()
        );
        patientNewWalletDao.addNewPatientNewWallet(newWallet);
    }

    private Patient getPatient(PatientNewWalletRegistrationsRequest request, ResourceBundle messages) {
        return patientDao.getPatientById(request.patientId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + request.patientId()
                        )
                );
    }

    private MainSurgicalList extractedMainSurgicalList(PatientNewWalletRegistrationsRequest request, ResourceBundle messages) {

        return mainSurgicalListDao.getSurgicalById(request.operationId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("operationNotFound") + request.operationId()
                        )
                );

    }

    private Doctor getDoctor(PatientNewWalletRegistrationsRequest request, ResourceBundle messages) {
        return (Doctor) getPatient(request, messages).getDoctor();
    }


    /**
     * 1. Get the MainSalaryTable by the doctor's id .
     * 2. if the MainSalaryTable is null, create a new MainSalaryTable .
     * 3. Create a new CustomSalary object with the data from the request.
     * 4. Add the new CustomSalary object to the database.
     * 5. Update the MainSalaryTable with the new CustomSalary object.
     * 6. Calculate the total salary for the MainSalaryTable.
     * 7. Return void.
     */
    private void insertToCustomSalaryForDoctors(PatientNewWalletRegistrationsRequest request, Doctor doctor, Patient patient) {

        MainSalaryTable mainSalaryTable = mainSalaryTableDao.getMainSalaryTableByUserId(doctor.getId()).orElse(null);
        if (mainSalaryTable == null) {
            mainSalaryTable = new MainSalaryTable();
            mainSalaryTable.setYearMonth(YearMonth.now());
            mainSalaryTable.setUsers(doctor);
            mainSalaryTableDao.addMainSalaryTable(mainSalaryTable);
        }

        CustomSalary newSalary = new CustomSalary();
        newSalary.setIsDown(false);
        newSalary.setTitle(request.operationName());
        newSalary.setNote(patient.getPatientData().getFullName());
        newSalary.setAmount(request.doctorPercentage());
        newSalary.setBaseUser(doctor);

        List<CustomSalary> existingCustomSalaries = mainSalaryTable.getCustomSalaries();
        if (existingCustomSalaries == null) {
            existingCustomSalaries = new ArrayList<>();
        }
        existingCustomSalaries.add(newSalary);
        mainSalaryTable.setCustomSalaries(existingCustomSalaries);

        customSalaryDao.createCustomSalary(newSalary);
        Double customSalary = customSalaryDao.getSumsCustomSalaryByMainSalaryId(mainSalaryTable.getMainSalaryId());
        mainSalaryTable.setTotalSalary(customSalary);
        mainSalaryTableDao.updateMainSalaryTable(mainSalaryTable);
    }

}
