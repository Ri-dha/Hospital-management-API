package com.azu.hospital.ph.StockMangment.Consumbles.Dto;

import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ConsumableDtoSpecialDtoMapper implements Function<Consumables, ConsumableDtoSpecial> {
  @Override
  public ConsumableDtoSpecial apply(Consumables consumables) {
    return new ConsumableDtoSpecial(
            consumables.getConsumableId(),
            consumables.getConsumableName()
    );
  }
}
