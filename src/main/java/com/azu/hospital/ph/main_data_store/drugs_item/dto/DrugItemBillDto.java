package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DrugItemBillDto {

        private Long id;
        private String drugName;
        private BigDecimal price;
        private Long count;
        private BigDecimal fullPrice ;

  public DrugItemBillDto() {
  }

  public DrugItemBillDto(Long id, String drugName, BigDecimal price
//                         Long count,  BigDecimal fullPrice
  ) {
    this.id = id;
    this.drugName = drugName;
    this.price = price;
    this.count = count;
    this.fullPrice = fullPrice;
  }
}
