package com.azu.hospital.ph.StockMangment.Supplier;

public record SupplierUpdateRequest(
        String supplierName,
        String supplierEmail,
        String supplierPhone,
        String address
) {
}
