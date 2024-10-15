package com.azu.hospital.ph.StockMangment.Supplier;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class SupplierDtoMapper implements Function<Supplier, SupplierDto> {
    @Override
    public SupplierDto apply(Supplier supplier) {
        return new SupplierDto(
                supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getSupplierEmail(),
                supplier.getSupplierPhone(),
                supplier.getAddress()
        );
    }

    public Supplier apply(SupplierDto supplierDto) {
        return new Supplier(
                supplierDto.getSupplierId(),
                supplierDto.getSupplierName(),
                supplierDto.getSupplierEmail(),
                supplierDto.getSupplierPhone(),
                supplierDto.getAddress()
        );
    }
}
