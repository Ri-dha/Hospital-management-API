package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.services;

import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dao.ConsumablesRequestTableListDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dto.ConsumablesRequestTableListDto;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dto.ConsumablesRequestTableListDtoMapper;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao.ConsumablesRequestTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dto.ConsumablesRequestTableDto;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dto.ConsumablesRequestTableDtoMapper;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumablesRequestGetServices {
    private final ConsumablesRequestTableDao dao;
    private final ConsumablesRequestTableDtoMapper mapper;

    private final ConsumablesRequestTableListDao consumablesRequestTableListDao;

    private final ConsumablesRequestTableListDtoMapper consumablesRequestTableListDtoMapper;

    @Autowired
    public ConsumablesRequestGetServices(@Qualifier("ConsumablesRequestTableJpa") ConsumablesRequestTableDao dao,
                                         @Qualifier("consumablesRequestTableDtoMapper") ConsumablesRequestTableDtoMapper mapper,
                                         @Qualifier("consumablesRequestTableListJpa") ConsumablesRequestTableListDao consumablesRequestTableListDao,
                                         @Qualifier("consumablesRequestTableListDtoMapper") ConsumablesRequestTableListDtoMapper consumablesRequestTableListDtoMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.consumablesRequestTableListDao = consumablesRequestTableListDao;
        this.consumablesRequestTableListDtoMapper = consumablesRequestTableListDtoMapper;
    }

    public Page<ConsumablesRequestTableDto> getAll(Pageable pageable) {
        Page<ConsumablesRequestTable> entitiesPage = dao.getAllRequests(pageable);
        List<ConsumablesRequestTableDto> list = entitiesPage.getContent()
                .stream()
                .map(mapper)
                .toList();
        return new PageImpl<>(list, pageable, entitiesPage.getTotalElements());
    }


    public Page<ConsumablesRequestTableListDto> getAllList(Pageable pageable) {
        return consumablesRequestTableListDao.getConsumablesRequestTableList(pageable)
                .map(consumablesRequestTableListDtoMapper);

    }

}

