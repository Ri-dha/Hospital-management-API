package com.azu.hospital.fileUploader;


import com.azu.hospital.utils.files.File;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/files")
@Validated
public class FileUploaderController {

    private final FileUploaderServices fileUploaderServices;


    public FileUploaderController(FileUploaderServices fileUploaderServices) {
        this.fileUploaderServices = fileUploaderServices;
    }



    @PostMapping("/upload")
    public File uploadFile(MultipartFile file) throws IOException {
        return fileUploaderServices.uploadMultiFile(file);
    }
}
