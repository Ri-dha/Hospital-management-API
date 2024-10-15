package com.azu.hospital.accounting.add_priceses.dto;

import com.azu.hospital.accounting.add_priceses.entity.Prices;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class PricesToPricesDtoMapper implements Function<Prices, PricesDto> {

    @Override
    public PricesDto apply(Prices prices) {
        if (prices == null) {
            return null;
        }

        return new PricesDto(
                prices.getId(),
                prices.getItemName(),
                prices.getFinalPrice(),
                prices.getType(),
                prices.getNote(),
                prices.getBillState(),
                prices.getCreatedAt(),
                prices.getUpdatedAt()
        );
    }
}

