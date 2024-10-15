package com.azu.hospital.utils.image;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ResourceLoader resourceLoader;
    private final HttpServletRequest request;

    @Value("${server.protocol}")
    private String protocol;

    @Autowired
    public ImageService(
            ImageRepository imageRepository,
             ResourceLoader resourceLoader,
            HttpServletRequest request
    ) {
        this.imageRepository = imageRepository;
        this.resourceLoader = resourceLoader;
        this.request = request;
    }

   @Value("${image.storage.path}")
    private String fileStoragePath;

    public String saveImage(MultipartFile imageFile) throws IOException {
        try {
            if (imageFile.isEmpty()) {
                return null;
            }
            String originalFileName = imageFile.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = "image_" + UUID.randomUUID() + extension;
            File file = new File(
                    resourceLoader.getResource("file:").getFile().getAbsolutePath() + fileStoragePath + fileName);
            Path filePath = file.toPath();
            file.getParentFile().mkdirs();
            Files.write(filePath, imageFile.getBytes(), StandardOpenOption.CREATE);
            String baseUrl = getBaseUrl(request);
            String imageUrl = baseUrl + "/" + fileName;
            return imageUrl;

        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }


    private String getBaseUrl(HttpServletRequest request) {
        String baseUrl = "";
        if (request != null) {
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int port = request.getServerPort();
            String contextPath = request.getContextPath();

            baseUrl = protocol + "://" + serverName;
            if ((scheme.equals("http") && port != 80) || (scheme.equals("https") && port != 443)) {
                baseUrl += ":" + port;
            }
            baseUrl += contextPath;
        }
        return baseUrl;
    }
    public Image updateImage(Long id, MultipartFile file, String imageName) throws IOException {
        Image existingImage = imageRepository.findById(id).orElse(null);
        if (existingImage == null) {
            return null;
        }

        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        File savedFile = new File(fileStoragePath + fileName);
        file.transferTo(savedFile);

        existingImage.setImageType(imageName);
        existingImage.setImageUrl("/assets/" + fileName);

        return imageRepository.save(existingImage);
    }



    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public String getFileStoragePath() {
        return fileStoragePath;
    }

    public void setFileStoragePath(String fileStoragePath) {
        this.fileStoragePath = fileStoragePath;
    }


    // TODO: 1/11/2024 not working yet 
    public void deleteImageByUrl(String imageUrl) {

        String fileStoragePathUser = "C:\\Users\\Qabeel\\Documents\\Hospital\\assets\\user\\images";
        try {
            URI uri = new URI(imageUrl);
            String path = uri.getPath();
            String[] pathSegments = path.split("/");
            String fileName = pathSegments[pathSegments.length - 1];
            File fileToDelete = new File(fileStoragePathUser, fileName);

            if (fileToDelete.exists() && fileToDelete.isFile()) {
                fileToDelete.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}