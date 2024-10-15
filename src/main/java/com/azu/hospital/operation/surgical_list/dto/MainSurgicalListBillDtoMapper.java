package com.azu.hospital.operation.surgical_list.dto;

import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MainSurgicalListBillDtoMapper implements Function<Operation,MainSurgicalListBillDto> {
  @Override
  public MainSurgicalListBillDto apply(Operation surgicalList) {
    return new MainSurgicalListBillDto(
            surgicalList.getId(),
            surgicalList.getSurgical().getSurgicalName(),
            surgicalList.getSurgical().getPrice()

    );
  }
}
