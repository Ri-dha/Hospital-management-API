package com.azu.hospital.lab_collection.test_list.dto;

import com.azu.hospital.lab_collection.test_list.entity.LabList;
import org.springframework.stereotype.Service;

@Service
public class LabListDtoMapper {

    public LabListDto toDto(LabList labList){
       return new LabListDto(
               labList.getId() ,
               labList.getLabName(),
               labList.getIqdPrice(),
               labList.getUsdPrice()
       );
    }
}
