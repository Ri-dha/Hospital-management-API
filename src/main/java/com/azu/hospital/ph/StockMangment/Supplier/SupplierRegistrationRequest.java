package com.azu.hospital.ph.StockMangment.Supplier;

public record SupplierRegistrationRequest (
        String supplierName,
        String supplierEmail,
        String supplierPhone,
        String address
){
}
