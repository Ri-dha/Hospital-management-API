//package com.azu.hospital.sitting.assets;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AssetCleanupRunner implements ApplicationRunner {
//
//    private final AssetCleanupService assetCleanupService;
//
//    @Autowired
//    public AssetCleanupRunner(AssetCleanupService assetCleanupService) {
//        this.assetCleanupService = assetCleanupService;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) {
//        assetCleanupService.cleanupAssets();
//    }
//}