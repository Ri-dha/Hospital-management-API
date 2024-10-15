package com.azu.hospital.ph.config;


import com.azu.hospital.ph.StockMangment.Supplier.Supplier;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


import java.io.IOException;

public class CustomSupplierSerializer extends JsonSerializer<Supplier> {
    @Override
    public void serialize(Supplier supplier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("supplierName", supplier.getSupplierName());
        jsonGenerator.writeStringField("supplierEmail", supplier.getSupplierEmail());
        jsonGenerator.writeStringField("supplierPhone", supplier.getSupplierPhone());
        jsonGenerator.writeStringField("address", supplier.getAddress());
        jsonGenerator.writeEndObject();
    }
}
