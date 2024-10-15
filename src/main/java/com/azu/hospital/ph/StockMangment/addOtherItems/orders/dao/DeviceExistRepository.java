package com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao;

import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.request.DeviceExistRequest;
import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DeviceExistRepository extends JpaRepository<DeviceExistsTable, Long> {

   Optional<DeviceExistsTable> findAllByDeviceId(Long deviceId);

   @Query("SELECT t FROM DeviceExistsTable t WHERE (:type IS NULL OR t.type = :type) AND t.isDeviceWorking = " +
           ":isWorking ")
   Page<DeviceExistsTable> findAllByType(@Param("type") DeviceType type , @Param("isWorking") Boolean isWorking ,
                                         Pageable pageable);

   @Query("SELECT i FROM  DeviceExistsTable i WHERE i.isDeviceWorking = :isDeviceWorking")
   List<DeviceExistsTable> findAllByIsDeviceWorking(@Param("isDeviceWorking") Boolean isDeviceWorking, Pageable pageable);

}
