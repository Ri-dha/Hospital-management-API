package com.azu.hospital.fileUploader;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileRepository;
import com.azu.hospital.utils.files.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class FileUploaderServices {

    private final ExceptionsMessageReturn messageReturn;

    private final FileService fileService;
    private final FileRepository fileRepository;



    public FileUploaderServices(ExceptionsMessageReturn messageReturn, FileService fileService, FileRepository fileRepository) {
        this.messageReturn = messageReturn;
        this.fileService = fileService;
        this.fileRepository = fileRepository;
    }



    public File uploadMultiFile( MultipartFile file) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        if (!file.isEmpty()) {
            String url = fileService.saveFileReturnPath(file);
            File newFile = new File(file.getContentType(), "Chat", url);
            fileRepository.save(newFile);
            return newFile;
        }else {
            throw new BadRequestException(
                    messages.getString("resourceNotFound")
            );
        }


    }


}
