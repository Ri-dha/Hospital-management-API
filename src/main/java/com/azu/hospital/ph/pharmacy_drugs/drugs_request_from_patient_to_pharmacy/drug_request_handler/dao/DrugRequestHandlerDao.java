package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface DrugRequestHandlerDao {

    List<DrugRequestHandler> addNewDrugRequest(List<DrugRequestHandler> request) ;

     void updateDrugRequest(DrugRequestHandler update);

     void updateDrugRequestHandler(DrugRequestHandler drugRequestHandler);

     void deleteDrugRequestHandlerById(Long id);

     Optional<DrugRequestHandler> getRequestById(Long id) ;

     List<DrugRequestHandler> getAllRequestsAccordingToUpdateAtOrCreateAtWithSorting() ;

     Page<DrugRequestHandler> getAllRequestsAccordingTo(Long userId, Long drugId, UnitInventoryRequestEnum requestStatus, Pageable pageable);

     Page<DrugRequestHandler> getAllRequestsByStatusAndLastUpdateAsd(UnitInventoryRequestEnum status, Pageable pageable) ;


     List<DrugRequestHandler> getAllDrugsRequestByPatientId(Long patientId);

     List<BillsDtoSum<String>> sumAllPatientQuantityDrugsRequestByPatientId(Long patientId);

     List<DrugRequestHandler> getAllReceivedDrugsRequestByPatentId(Long patientId);


     Long repositoryCount();

}