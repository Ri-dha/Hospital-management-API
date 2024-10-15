package com.azu.hospital.ph.StockMangment.Consumbles.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

@Service
public class ConsumablesBarcodeService {


    public byte[] generateCode128BarcodeImage(int width, int height) throws WriterException, IOException {
    Long randomNumber = generateRandomLong();
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(String.valueOf(randomNumber), BarcodeFormat.CODE_128, width, height);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        }
    }


    private Long generateRandomLong() {
        SecureRandom secureRandom = new SecureRandom();
        return Math.abs(secureRandom.nextLong());
    }
}
