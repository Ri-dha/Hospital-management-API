package com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao;

import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("bottleOrderRepo")
public class BottleOrderDataJpa implements BottleOrderDao {

    private final BottleOrderRepository bottleOrderRepository;

    @Autowired
    public BottleOrderDataJpa(BottleOrderRepository bottleOrderRepository) {
        this.bottleOrderRepository = bottleOrderRepository;
    }

    @Override
    public void createNewOrder(BottleOrder bottleOrder) {
         bottleOrderRepository.save(bottleOrder);
    }

    @Override
    public List<BottleOrder> findTopByStateOrderByCreatedAtDesc() {
        return bottleOrderRepository.findTop6ByStateOrderByCreatedAtDesc(
                BottleStateEnum.NoArchive
        );
    }

    @Override
    public Page<BottleOrder> getAllArchivedBottleOrder(BottleStateEnum state, String patientName,
                                                       Pageable pageable) {
        return bottleOrderRepository.getAllByStateAndPatientPatientDataFullNameContainingIgnoreCaseOrderByCreatedAtAscUpdatedAt(
                state,
                patientName,
                pageable

        );
    }

    @Override
    public Optional<BottleOrder> findBottleOrderById(Long bottleOrderId) {
        return bottleOrderRepository.findById(bottleOrderId);
    }

    @Override
    public Page<BottleOrder> getNewOrder(
            BottleStateEnum state,
            Boolean isReserved,
            String search,
            Pageable pageable
    ) {
        return bottleOrderRepository.getAllByStateAndIsReservedAndPatientPatientDataFullNameContainingIgnoreCaseOrderByPatientMedicalHistoryTriage(
                state,
                isReserved ,
                search,
                pageable
        );
    }

    @Override
    public void updateBottleOrder(BottleOrder bottleOrder) {
       bottleOrderRepository.save(bottleOrder);
    }

    @Override
    public Page<BottleOrder> getAllNewRequestWithFilter(BottleStateEnum state, String search, Pageable pageable) {
        return bottleOrderRepository.getAllNewRequestWithFilter(
                state,
                search,
                pageable
        );
    }

    @Override
    public Page<BottleOrder> findByBottleTypeAndStateIn(BottleTypeEnum bottleType, List<BottleStateEnum> states, Pageable pageable) {
        return bottleOrderRepository.findByBottleTypeAndStateIn(
                bottleType,
                states,
                pageable
        );
    }


    @Override
    public Optional<BottleOrder> findBottleOrderInfoById(Long id) {
        return bottleOrderRepository.findById(id);
    }

    @Override
    public void updateManyBottleOrder(List<BottleOrder> bottleOrders) {
        bottleOrderRepository.saveAll(bottleOrders);
    }

    @Override
    public Integer sumCountOfBottleInOrder(BloodGroupEnum bloodGroup, BottleTypeEnum bottleType) {
        return bottleOrderRepository.sumCountByBottleOrderStateAndBloodGroupAndBottleType(
                BottleStateEnum.NoArchive,
                bloodGroup,
                bottleType
        );
    }


}
