package com.azu.hospital.sitting.assets;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class AssetCleanupService {

    @Value("${file.storage.path}")
    private  String fileStoragePath;

    public boolean cleanupAssets() {
        File assetsDirectory = new File("C:\\Users\\Qabeel\\Documents\\Hospital\\assets\\user\\images");

        if (assetsDirectory.exists() && assetsDirectory.isDirectory()) {
            deleteDirectory(assetsDirectory);
        }
        return assetsDirectory.exists();
    }


    private void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }

        directory.delete();
    }




}

