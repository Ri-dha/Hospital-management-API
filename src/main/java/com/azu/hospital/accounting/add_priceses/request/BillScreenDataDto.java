package com.azu.hospital.accounting.add_priceses.request;


import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillScreenDataDto<T> {

    private String title;

   private  List<BillsDtoSum<T>> content;


}
