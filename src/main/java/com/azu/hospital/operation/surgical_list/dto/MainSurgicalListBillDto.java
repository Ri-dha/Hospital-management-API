package com.azu.hospital.operation.surgical_list.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MainSurgicalListBillDto {


  private Long id;
  private String drugName;
  private BigDecimal price;
//  private Long count;
//  private BigDecimal fullPrice ;

  public MainSurgicalListBillDto() {
  }

  public MainSurgicalListBillDto(Long id, String drugName, BigDecimal price
//          , Long count ,BigDecimal fullPrice

  ) {
    this.id = id;
    this.drugName = drugName;
    this.price = price;
//    this.count = count;
//    this.fullPrice = fullPrice;
  }
}
