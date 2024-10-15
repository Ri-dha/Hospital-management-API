package com.azu.hospital.utils.files;

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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final ResourceLoader resourceLoader;
    private final HttpServletRequest request;
    private boolean mkdirs;
    @Value("${file.storage.path}")
    private String fileStoragePath;


    @Value("${server.protocol}")
    private String protocol;

    @Autowired
    public FileService(FileRepository fileRepository,
                       @Qualifier("webApplicationContext") ResourceLoader resourceLoader, HttpServletRequest request) {
        this.fileRepository = fileRepository;
        this.resourceLoader = resourceLoader;
        this.request = request;
    }

    public String saveFile(MultipartFile imageFile) throws IOException {
        try {
            if (imageFile.isEmpty()) {
                return "document file is empty";
            }
            String originalFileName = imageFile.getOriginalFilename();
            String baseUrl = null;
            String fileName = null;
            if (originalFileName != null) {

                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                fileName = "document_" + UUID.randomUUID() + extension;
                File file = new File(
                        resourceLoader.getResource("file:").getFile().getAbsolutePath() + fileStoragePath + fileName);
                Path filePath = file.toPath();
                mkdirs = file.getParentFile().mkdirs();
                Files.write(filePath, imageFile.getBytes(), StandardOpenOption.CREATE);
                baseUrl = getBaseUrl(request);

            }
            return baseUrl + "/" + fileName;


        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }

    }



    public String saveFileReturnPath(MultipartFile imageFile) throws IOException {
        try {
            if (imageFile.isEmpty()) {
                return "document file is empty";
            }
            String originalFileName = imageFile.getOriginalFilename();
            String baseUrl = null;
            String fileName = null;
            if (originalFileName != null) {

                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                fileName = "document_" + UUID.randomUUID() + extension;
                File file = new File(
                        resourceLoader.getResource("file:").getFile().getAbsolutePath() + fileStoragePath + fileName);
                Path filePath = file.toPath();
                mkdirs = file.getParentFile().mkdirs();
                Files.write(filePath, imageFile.getBytes(), StandardOpenOption.CREATE);
                baseUrl = getBaseUrl(request);

            }
            return fileName;


        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }

    }



    public List<String> saveFiles(List<MultipartFile> files) throws IOException {
        List<String> fileUrls = new ArrayList<>();
        try {

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String originalFileName = file.getOriginalFilename();
                    if (originalFileName != null) {
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                        String fileName = "document_" + UUID.randomUUID() + extension;
                        File savedFile = new File(resourceLoader.getResource("file:").getFile().getAbsolutePath() + fileStoragePath + fileName);
                        Path filePath = savedFile.toPath();
                        mkdirs = savedFile.getParentFile().mkdirs();
                        Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
                        String baseUrl = getBaseUrl(request);
                        fileUrls.add(baseUrl + "/" + fileName);
                    }
                }
            }
            return fileUrls;

        } catch (IOException e) {
            e.printStackTrace();

        }

        return fileUrls;
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

    // TODO: 11/14/2023 error method
    public com.azu.hospital.utils.files.File updateImage(Long id, MultipartFile file, String imageName) throws IOException {

        com.azu.hospital.utils.files.File existingFile = fileRepository.findById(id).orElse(null);
        if (existingFile == null) {
            return null;
        }

        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        File savedFile = new File(fileStoragePath + fileName);
        file.transferTo(savedFile);

        existingFile.setFileType(imageName);
        existingFile.setFileUrl("/assets/" + fileName);

        return fileRepository.save(existingFile);
    }


    public void deleteFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }


    public com.azu.hospital.utils.files.File getFileById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    public void deleteImage(Long id) {
        fileRepository.deleteById(id);
    }

    public String getFileStoragePath() {
        return fileStoragePath;
    }

    public void setFileStoragePath(String fileStoragePath) {
        this.fileStoragePath = fileStoragePath;
    }


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