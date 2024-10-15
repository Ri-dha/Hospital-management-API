package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.services;

import com.azu.hospital.accounting.all_item_expanse.ultrasound.service.IPatientUltrasoundExpanseResultTableAddServices;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dao.InternalUltrasoundResultDao;
import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dto.InternalUltrasoundResultDto;
import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dto.InternalUltrasoundResultDtoMapper;
import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.entity.InternalUltrasoundResult;
import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.request.CreateInternalUltrasoundResultRequest;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
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
public class InternalUltrasoundResultService {

    private final InternalUltrasoundResultDao internalUltrasoundResultDao;
    private final ExceptionsMessageReturn messageReturn;

    private final InternalUltrasoundResultDtoMapper mapper;

    private final FileService fileService;
    private final InternalUltrasoundTestDao internalUltrasoundTestDao;

    private final IPatientUltrasoundExpanseResultTableAddServices patientUltrasoundExpanseResultTableAddServices;


    @Autowired
    public InternalUltrasoundResultService(
            @Qualifier("internalUltrasoundResultRepo") InternalUltrasoundResultDao internalUltrasoundResultDao, ExceptionsMessageReturn messageReturn,
            @Qualifier("internalUltrasoundResultDtoMapper") InternalUltrasoundResultDtoMapper mapper,
            FileService fileService,
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao,
            @Qualifier("PatientUltrasoundExpanseResultTableAddServicesImp") IPatientUltrasoundExpanseResultTableAddServices patientUltrasoundExpanseResultTableAddServices) {
        this.internalUltrasoundResultDao = internalUltrasoundResultDao;
        this.messageReturn = messageReturn;
        this.mapper = mapper;
        this.fileService = fileService;
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
        this.patientUltrasoundExpanseResultTableAddServices = patientUltrasoundExpanseResultTableAddServices;
    }



    public void createResult(CreateInternalUltrasoundResultRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        InternalUltrasoundTest test = internalUltrasoundTestDao.findById(request.getTestId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        if (test.getState() != UltrasoundOrderState.Received){
            throw new BadRequestException(
                    messages.getString("cannotAccept" )+"or"+ messages.getString("alreadyExist")
            );
        }
        InternalUltrasoundResult result = new InternalUltrasoundResult(request.getNote());

        result.setInternalUltrasoundTest(test);

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
        internalUltrasoundResultDao.createNewResult(result);
        test.setState(UltrasoundOrderState.Completed);
        internalUltrasoundTestDao.updateInternalUltrasoundTest(test);
        patientUltrasoundExpanseResultTableAddServices.addPatientUltrasoundExpanseResultTable(test.getPatient().getId());


    }


    public InternalUltrasoundResultDto getResultByTestId(Long testId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        internalUltrasoundTestDao.findById(testId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        return mapper.toDto(
                internalUltrasoundResultDao.getByTestId(testId).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                )
        ) ;
    }
}
