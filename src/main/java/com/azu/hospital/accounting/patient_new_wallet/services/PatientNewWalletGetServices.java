package com.azu.hospital.accounting.patient_new_wallet.services;

import com.azu.hospital.accounting.patient_new_wallet.dao.PatientNewWalletDao;
import com.azu.hospital.accounting.patient_new_wallet.dto.PatientNewWalletDto;
import com.azu.hospital.accounting.patient_new_wallet.dto.PatientNewWalletDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientNewWalletGetServices {

    private final PatientNewWalletDao dao;

    private final PatientNewWalletDtoMapper mapper;

    public PatientNewWalletGetServices(PatientNewWalletDao dao, PatientNewWalletDtoMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


    public PatientNewWalletDto getPatientNewWalletById(Long id){
        return dao.getPatientNewWalletById(id)
                .stream()
                .map(mapper)
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(
                        "operation cost not found")
                );
    }

    public Page<PatientNewWalletDto> getAllPatientNewWallet(Pageable pageable){
        return dao.getAllPatientNewWallet(pageable)
                .map(mapper);
    }

    public Long totalPatientNewWalletCount(){
        return dao.totalPatientNewWalletCount();
    }

}
