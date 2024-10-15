package com.azu.hospital.ph.StockMangment.Offers.list;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DrugOfferDtoMapper {
    private final ModelMapper modelMapper;

    public DrugOfferDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DrugOfferDto mapToDto(DrugsOffer drugOffer) {
        return  modelMapper.map(drugOffer, DrugOfferDto.class);
    }

    public DrugsOffer mapToEntity(DrugOfferDto drugOfferDto) {
        return modelMapper.map(drugOfferDto, DrugsOffer.class);
    }
}
