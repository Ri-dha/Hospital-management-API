package com.azu.hospital.ph.StockMangment.Offers.list;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ConsumablesOfferDtoMapper {
    private final ModelMapper modelMapper;

    public ConsumablesOfferDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ConsumablesOfferDto mapToDto(ConsumablesOffer consumableOffer) {
       return modelMapper.map(consumableOffer, ConsumablesOfferDto.class);
    }

    public ConsumablesOffer mapToEntity(ConsumablesOfferDto consumableOfferDto) {
       return modelMapper.map(consumableOfferDto, ConsumablesOffer.class);
    }
}
