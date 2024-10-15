package com.azu.hospital.accounting.add_priceses.request;

import com.azu.hospital.accounting.add_priceses.dao.PricesDao;
import com.azu.hospital.accounting.add_priceses.dto.PricesDto;
import com.azu.hospital.accounting.add_priceses.dto.PricesToPricesDtoMapper;
import com.azu.hospital.accounting.add_priceses.entity.Prices;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.enums.EnumItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricesService {
    private final PricesDao dao;
    private final PricesToPricesDtoMapper mapper;

    @Autowired
    public PricesService(PricesDao dao, PricesToPricesDtoMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public Page<PricesDto> getAllItemPrices(String itemType, String name, Pageable pageable) {
        EnumItemType type = null;
        if (itemType != null && !itemType.isEmpty()) {
            type = EnumItemType.valueOf(itemType);
        }
        List<PricesDto> dtoList =  dao.getAllItemPrices(type, name, pageable)
                .stream()
                .map(mapper)
                .toList();
        return new PageImpl<>(dtoList,pageable, pageable.getPageSize());
    }

    public PricesDto getItemPricesById(Long id) {
        return dao.getItemPriceById(id)
                .stream()
                .map(mapper)
                .findFirst()
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                "No such item with id " + id
                        )
                );
    }
}
