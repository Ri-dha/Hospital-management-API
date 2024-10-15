package com.azu.hospital.operation.surgical_list.dto;

import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class MainSurgicalListDtoMapper implements Function<MainSurgicalList, MainSurgicalListDto> {
    @Override
    public MainSurgicalListDto apply(MainSurgicalList mainSurgicalList) {
        return new MainSurgicalListDto(
                mainSurgicalList.getId(),
                mainSurgicalList.getSurgicalName(),
                mainSurgicalList.getPrice(),
                mainSurgicalList.getNote()
        );
    }
}
