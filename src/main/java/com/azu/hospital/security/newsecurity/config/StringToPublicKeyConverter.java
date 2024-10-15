//package com.azu.hospital.security.newsecurity.config;
//
//import org.springframework.stereotype.Component;
//
//import java.security.KeyFactory;
//import java.security.PublicKey;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//import org.springframework.core.convert.converter.Converter;
//
//@Component
//public class StringToPublicKeyConverter implements Converter<String, PublicKey> {
//
//    @Override
//    public PublicKey convert(String key) {
//        try {
//            byte[] keyBytes = Base64.getDecoder().decode(key);
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Change to your desired algorithm if not RSA
//            return keyFactory.generatePublic(keySpec);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Failed to convert the provided public key string to a PublicKey.", e);
//        }
//    }
//
//}