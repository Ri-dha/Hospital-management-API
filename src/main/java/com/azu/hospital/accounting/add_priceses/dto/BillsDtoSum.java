package com.azu.hospital.accounting.add_priceses.dto;


import com.azu.hospital.utils.enums.StockBillState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillsDtoSum<T> {

    private Long id;

    private Integer count;

    private T name;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private StockBillState state;




}
