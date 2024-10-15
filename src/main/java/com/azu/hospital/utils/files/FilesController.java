package com.azu.hospital.utils.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/files")
@CrossOrigin

public class FilesController {

    private final FileService fileService;

    @Autowired
    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String saveFile(MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }

    @PostMapping("/files")
    public void saveFiles(List<MultipartFile> files) throws IOException {
        fileService.saveFiles(files);
    }
}
