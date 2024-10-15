package com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao;

import com.azu.hospital.lab_collection.blood_bank.BottleOrder.entity.BottleOrder;
import com.azu.hospital.utils.enums.bottles.BloodGroupEnum;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BottleOrderDao {

    void createNewOrder(BottleOrder bottleOrder);

    List<BottleOrder> findTopByStateOrderByCreatedAtDesc();
    Page<BottleOrder> getAllArchivedBottleOrder(BottleStateEnum state,String patientName , Pageable pageable);
    Optional<BottleOrder> findBottleOrderById(Long bottleOrderId);

    Page<BottleOrder> getNewOrder(BottleStateEnum state,Boolean isReserved , String search , Pageable pageable);
    void updateBottleOrder(BottleOrder bottleOrder);

    Page<BottleOrder> getAllNewRequestWithFilter(BottleStateEnum state, String search, Pageable pageable);

    Page<BottleOrder> findByBottleTypeAndStateIn(BottleTypeEnum bottleType, List<BottleStateEnum> states, Pageable pageable);


    Optional<BottleOrder> findBottleOrderInfoById(Long id);

    void updateManyBottleOrder(List<BottleOrder> bottleOrders);

    Integer sumCountOfBottleInOrder(BloodGroupEnum bloodGroup, BottleTypeEnum bottleType);

}
