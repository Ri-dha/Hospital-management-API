package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface DrugRequestHandlerRepository extends JpaRepository<DrugRequestHandler, Long> {

    @Query("SELECT drh FROM DrugRequestHandler drh " +
            "ORDER BY COALESCE(drh.updateAt, drh.createAt) ASC")
    List<DrugRequestHandler> getAllRequestsAccordingToUpdateAtOrCreateAtWithSorting();

    @Query("SELECT drh FROM DrugRequestHandler drh " +
            "WHERE (:userId IS NULL OR drh.signature.id = :userId) " +
            "AND (:drugId IS NULL OR drh.drugId = :drugId) " +
            "AND (:requestStatus IS NULL OR drh.requestStatus = :requestStatus)")
    Page<DrugRequestHandler> getAllRequestsAccordingTo(
            @Param("userId") Long userId,
            @Param("drugId") Long drugId,
            @Param("requestStatus") UnitInventoryRequestEnum requestStatus,
            Pageable pageable);

    @Query("SELECT drh FROM DrugRequestHandler drh WHERE drh.requestStatus = :status ORDER BY COALESCE(drh.updateAt, drh.createAt) ASC")
    Page<DrugRequestHandler> getAllRequestsByStatusAndLastUpdateAsd(@Param("status") UnitInventoryRequestEnum status, Pageable pageable);


    @Query("SELECT r FROM DrugRequestHandler r WHERE r.patient.id = :patientId")
    List<DrugRequestHandler> getAllRequestsByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT r FROM DrugRequestHandler r WHERE r.patient.id = :patientId AND r.requestStatus = 'Accepted' and r.isArchived = false")
    List<DrugRequestHandler> findAllReceivedDrugsRequestByPatientId(@Param("patientId") Long patientId);



    @Query("SELECT new  com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum" +
            "(s.drugId, CAST(SUM(s.quantity) AS integer)," +
            " MAX(s.drugsItems.itemName), MAX(s.drugsItems.price)," +
            " CAST(MAX(s.drugsItems.price) * CAST(SUM(s.quantity) AS integer) AS BIGDECIMAL ) ) " +
            "FROM DrugRequestHandler s WHERE s.patient.id = :patientId AND s.requestStatus = com.azu.hospital.utils.enums.UnitInventoryRequestEnum.Accepted  GROUP BY s.drugId")
    List<BillsDtoSum<String>> sumAllDrugsItemQuantityWithSameDrugIdForSamePatientId(@Param("patientId") Long patientId);



    @Query("SELECT COUNT(drh) FROM DrugRequestHandler drh where drh.isArchived = false or drh.requestStatus = 'Accepted'")
    Long repositoryCount();



}
