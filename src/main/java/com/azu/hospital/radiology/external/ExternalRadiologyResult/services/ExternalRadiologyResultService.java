package com.azu.hospital.radiology.external.ExternalRadiologyResult.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.external.ExternalRadiologyResult.dao.ExternalRadiologyResultDao;
import com.azu.hospital.radiology.external.ExternalRadiologyResult.dto.ExternalRadiologyResultDto;
import com.azu.hospital.radiology.external.ExternalRadiologyResult.dto.ExternalRadiologyResultDtoMapper;
import com.azu.hospital.radiology.external.ExternalRadiologyResult.entity.ExternalRadiologyResult;
import com.azu.hospital.radiology.external.ExternalRadiologyResult.request.CreateExternalRadiologyResultRequest;
import com.azu.hospital.radiology.external.dao.ExternalRadiologyTestDao;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import jakarta.servlet.http.HttpServletRequest;
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
public class ExternalRadiologyResultService extends GenericBaseService {

    private final ExternalRadiologyResultDao externalRadiologyResultDao;
    private final ExceptionsMessageReturn messageReturn;

    private final ExternalRadiologyResultDtoMapper mapper;

    private final FileService fileService;
    private final ExternalRadiologyTestDao externalRadiologyTestDao;

    @Autowired
    public ExternalRadiologyResultService(
            @Qualifier("externalRadiologyResultRepo") ExternalRadiologyResultDao externalRadiologyResultDao,
            ExternalRadiologyResultDtoMapper mapper,
            FileService fileService,
            @Qualifier("externalRadiologyTestRepo") ExternalRadiologyTestDao externalRadiologyTestDao,
            HttpServletRequest request,
            JwtService jwtService, ExceptionsMessageReturn messageReturn

    ) {
        super(jwtService, request);
        this.externalRadiologyResultDao = externalRadiologyResultDao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.externalRadiologyTestDao = externalRadiologyTestDao;
        this.messageReturn = messageReturn;
    }


    public void createResult(CreateExternalRadiologyResultRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        ExternalRadiologyTest test = externalRadiologyTestDao.findById(request.getTestId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("ExternalRadiologyTestNotFound")+" "+request.getTestId()
                )
        );

        if (test.getState() == RadiologyOrderState.Rejected){
            throw new BadRequestException(
                    messages.getString("cannotAccept" )

            );
        }

        ExternalRadiologyResult result = new ExternalRadiologyResult(request.getNote());
        result.setExternalRadiologyTest(test);

        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            List<File> fileList = new ArrayList<>();
            for (MultipartFile files : request.getFiles()) {
                if (!files.isEmpty()) {
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType(), "Radiology", url);
                    fileList.add(file);
                }
            }
            result.setFiles(fileList);
        }

        externalRadiologyResultDao.createNewResult(result);
        test.setState(RadiologyOrderState.Completed);
        externalRadiologyTestDao.updateExternalRadiologyTest(test);
    }

    public ExternalRadiologyResultDto getResultByTestId(Long testId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        externalRadiologyTestDao.findById(testId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("ExternalRadiologyTestNotFound")+" "+testId
                )
        );

        return mapper.apply(
                externalRadiologyResultDao.getByTestId(testId).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("ExternalRadiologyResultNotFound")+" "+testId
                        )
                )
        );
    }

}
