//package com.azu.hospital.security.newsecurity.config.exceptions_security;
//
//
//
//import org.springframework.core.annotation.Order;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.security.KeyFactory;
//import java.security.PrivateKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.util.Base64;
//@Order
//@Component
//public class StringToPrivateKeyConverter implements Converter<String, PrivateKey> {
//
//    @Override
//    public PrivateKey convert(String key) {
//        try {
//            key = key.trim();
//
//            if (key.startsWith("-----BEGIN PRIVATE KEY-----") && key.endsWith("-----END PRIVATE KEY-----")) {
//                key = key.replace("-----BEGIN PRIVATE KEY-----", "");
//                key = key.replace("-----END PRIVATE KEY-----", "");
//            }
//            byte[] keyBytes = Base64.getDecoder().decode(key);
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            return keyFactory.generatePrivate(keySpec);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Failed to convert the provided key string to a PrivateKey.", e);
//        }
//    }
//}