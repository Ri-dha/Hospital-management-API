package com.azu.hospital.accounting.patientBill.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.patientBill.dao.PatientBillDao;
import com.azu.hospital.accounting.patientBill.dto.PatientBillDto;
import com.azu.hospital.accounting.patientBill.entity.PatientBill;
import com.azu.hospital.accounting.patientBill.requests.PatientBillCreateRequest;
import com.azu.hospital.accounting.patientBill.requests.PatientBillUpdateRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.accounting.patientBill.dto.PatientBillDtoMapper;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Service
public class PatientBillService {

    private final PatientBillDao patientBillDao;
    private final PatientBillDtoMapper mapper;
    private final PatientDao patientDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PatientBillService(
            @Qualifier("PatientBillJpa") PatientBillDao patientBillDao,
            PatientBillDtoMapper mapper,
            @Qualifier("patientRepo") PatientDao patientDao, ExceptionsMessageReturn messageReturn
    ) {
        this.patientBillDao = patientBillDao;
        this.mapper = mapper;
        this.patientDao = patientDao;
        this.messageReturn = messageReturn;
    }


    public void CreateNewPatientBill(PatientBillCreateRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(request.patientId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound"))
        );

        PatientBill patientBill = new PatientBill(
                request.note(),
                request.quantity(),
                request.price(),
                request.fullPrice(),
                request.discount(),
                request.type(),
                request.place()
        );

        patientBill.setPatient(patient);

        patientBillDao.createPatientBill(patientBill);

    }


    public void updatePatientBill(Long patientBillId, PatientBillUpdateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PatientBill existingBill = patientBillDao.findPatientBillById(patientBillId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        boolean updated = false;

        if (request.note() != null && !Objects.equals(request.note(), existingBill.getNote())) {
            existingBill.setNote(request.note());
            updated = true;
        }
        if (request.quantity() != null && request.quantity() != existingBill.getQuantity()) {
            existingBill.setQuantity(request.quantity());
            updated = true;
        }
        if (request.price() != null && !Objects.equals(request.price(), existingBill.getPrice())) {
            existingBill.setPrice(request.price());
            updated = true;
        }
        if (request.fullPrice() != null && !Objects.equals(request.fullPrice(), existingBill.getFullPrice())) {
            existingBill.setFullPrice(request.fullPrice());
            updated = true;
        }
        if (request.discount() != null && !Objects.equals(request.discount(), existingBill.getDiscount())) {
            existingBill.setDiscount(request.discount());
            updated = true;
        }
        if (request.type() != null && !Objects.equals(request.type(), existingBill.getType())) {
            existingBill.setType(request.type());
            updated = true;
        }
        if (request.place() != null && !Objects.equals(request.place(), existingBill.getPlace())) {
            existingBill.setPlace(request.place());
            updated = true;
        }

        if (!updated) {
            throw new IllegalStateException(
                    messages.getString("noChanges"));
        }

        patientBillDao.updatePatientBill(existingBill);
    }

    public PatientBillDto getById(Long patientBillId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PatientBill patientBill = patientBillDao.findPatientBillById(patientBillId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound"))
        );

        return mapper.toDto(patientBill);
    }


    public Page<PatientBillDto> getByPatientId(Long patientId , Pageable pageable){
        return patientBillDao.getPatientBillByPatientId(patientId , pageable).map(mapper::toDto);
    }


    public Page<PatientBillDto> getAll(Pageable pageable){
        return patientBillDao.getAll(pageable).map(mapper::toDto);
    }



}
