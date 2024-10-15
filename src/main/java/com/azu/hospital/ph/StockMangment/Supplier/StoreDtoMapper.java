package com.azu.hospital.ph.StockMangment.Supplier;

import org.modelmapper.ModelMapper;

public class StoreDtoMapper {
    private  final ModelMapper modelMapper;

    public StoreDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SupplierDto mapToDto(Supplier supplier){
        SupplierDto supplierDto = modelMapper.map(supplier, SupplierDto.class);
        return supplierDto;
    }
}
