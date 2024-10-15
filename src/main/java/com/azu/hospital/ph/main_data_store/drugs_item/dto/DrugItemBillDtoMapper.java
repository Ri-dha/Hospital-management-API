package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DrugItemBillDtoMapper implements Function<DrugsItem,DrugItemBillDto> {


  @Override
  public DrugItemBillDto apply(DrugsItem drugsItem) {
    return new DrugItemBillDto(
            drugsItem.getId(),
            null,
            drugsItem.getPrice()

    );
  }
}
