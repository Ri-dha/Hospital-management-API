package com.azu.hospital.ph.Sales.services;


import com.azu.hospital.ph.Sales.dao.SoldBillDao;
import com.azu.hospital.ph.Sales.dto.SoldBillDto;
import com.azu.hospital.ph.Sales.dto.SoldBillDtoMapper;
import com.azu.hospital.ph.Sales.dto.SoldBillWithTotalPrice;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoldBillServicesGetter {
    private final SoldBillDao soldBillDao;
    private final SoldBillDtoMapper dtoMapper;


   @Autowired
    public SoldBillServicesGetter(SoldBillDao soldBillDao, SoldBillDtoMapper dtoMapper) {
        this.soldBillDao = soldBillDao;
        this.dtoMapper = dtoMapper;


   }


    public SoldBillDto getBillById(Long billId) {
        return soldBillDao.getBillById(billId)
                .map(dtoMapper)
                .orElse(null);
    }


    public SoldBillWithTotalPrice getAllBills(
            LocalDate startDate,
            LocalDate endDate,
             Pageable pageable
    ) {
        Page<SoldBill> soldBills = soldBillDao.getAllSoldBillsSortedByNameIgnoreCaseAndCreatedAtDesc(startDate, endDate,
                pageable);
        List<SoldBillDto> soldBillDtoList = soldBills
                .stream()
                .map(dtoMapper)
                .collect(Collectors.toList());

        BigDecimal totalFullPrice = soldBillDao.countAllTotalPricesForAllSolidBillAndIsArchiveFalse();

        Page<SoldBillDto>  soldBillDtos = new PageImpl<>(soldBillDtoList, pageable, soldBills.getTotalElements());
        return new SoldBillWithTotalPrice(soldBillDtos, totalFullPrice);
    }




    public List<ItemSummary> findMostSoldItems(int topN) {
        Pageable pageable = PageRequest.of(0, topN);
        List<Object[]> results = soldBillDao.findMostSoldItems(pageable);

        List<ItemSummary> summaries = new ArrayList<>();
        for (Object[] result : results) {
            String itemName = (String) result[0];
            long quantity = ((Number) result[1]).longValue();
            summaries.add(new ItemSummary(itemName, quantity));
        }
        return summaries;
    }

    public record ItemSummary(String itemName, long quantity) {
    }

    public SoldBillDtoMapper getDtoMapper() {
        return this.dtoMapper;
    }







}
