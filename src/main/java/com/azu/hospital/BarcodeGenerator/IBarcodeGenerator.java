package com.azu.hospital.BarcodeGenerator;

public interface IBarcodeGenerator {

    String generateEAN13BarcodeImage(String barcodeText) throws Exception;
}
