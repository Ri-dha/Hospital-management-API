package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dao.ExternalUltrasoundResultDao;
import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dto.ExternalUltrasoundResultDto;
import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dto.ExternalUltrasoundResultDtoMapper;
import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.entity.ExternalUltrasoundResult;
import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.request.CreateExternalUltrasoundResultRequest;
import com.azu.hospital.ultrasound.external.dao.ExternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ExternalUltrasoundResultService {
    private final ExceptionsMessageReturn messageReturn;

    private final ExternalUltrasoundResultDao externalUltrasoundResultDao;

    private final ExternalUltrasoundResultDtoMapper mapper;

    private final FileService fileService;
    private final ExternalUltrasoundTestDao externalUltrasoundTestDao;

    @Autowired
    public ExternalUltrasoundResultService(
            ExceptionsMessageReturn messageReturn, @Qualifier("externalUltrasoundResultRepo") ExternalUltrasoundResultDao externalUltrasoundResultDao,
            ExternalUltrasoundResultDtoMapper mapper,
            FileService fileService,
            @Qualifier("externalUltrasoundTestRepo") ExternalUltrasoundTestDao externalUltrasoundTestDao) {
        this.messageReturn = messageReturn;
        this.externalUltrasoundResultDao = externalUltrasoundResultDao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.externalUltrasoundTestDao = externalUltrasoundTestDao;
    }



    public void createResult(CreateExternalUltrasoundResultRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        ExternalUltrasoundTest test = externalUltrasoundTestDao.findById(request.getTestId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        if (test.getState() != UltrasoundOrderState.Received){
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }
        ExternalUltrasoundResult result = new ExternalUltrasoundResult(request.getNote());

        result.setExternalUltrasoundTest(test);

        if (request.getFiles() != null && !request.getFiles().isEmpty()){
            List<File> fileList = new ArrayList<>();
            for(MultipartFile files: request.getFiles()){
                if (!files.isEmpty()){
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType() , "Ultrasound" ,url );
                    fileList.add(file);
                }
            }
            result.setFiles(fileList);
        }

        externalUltrasoundResultDao.createNewResult(result);
        test.setState(UltrasoundOrderState.Completed);
        externalUltrasoundTestDao.updateExternalUltrasoundTest(test);

    }


    public ExternalUltrasoundResultDto getResultByTestId(Long testId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        externalUltrasoundTestDao.findById(testId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        return mapper.apply(
                externalUltrasoundResultDao.getByTestId(testId).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                )
        ) ;
    }
}
