package com.azu.hospital.Patients.Patient.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Component
public class QrCodeService {

    private final HttpServletResponse response;
    @Value("${qr.code.url}")
    private String qrCodeUrl;

    public QrCodeService(HttpServletResponse response) {
        this.response = response;
    }

    public String getQrCodeUrl(Long id) {
        String url = qrCodeUrl + id;
        String qrUrl = "https://chart.googleapis.com/chart?cht=qr";
        qrUrl += "&chl=" + url;
        qrUrl += "&choe=UTF-8";
        qrUrl += "&chs=300x300";
        return qrUrl;
    }



//    public byte[] generateQRCode(Long id) throws WriterException, IOException {
//        QRCodeWriter barcodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix =
//                barcodeWriter.encode(qrCodeUrl + id, BarcodeFormat.CODE_128, 200, 200);
//
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
//            return outputStream.toByteArray();
//        }
//    }



}
