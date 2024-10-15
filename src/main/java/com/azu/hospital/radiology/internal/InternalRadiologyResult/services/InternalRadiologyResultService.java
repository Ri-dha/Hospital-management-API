package com.azu.hospital.radiology.internal.InternalRadiologyResult.services;

import com.azu.hospital.accounting.all_item_expanse.radiology.service.IPatientRadiologyExpanseResultTableAddServices;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.dao.InternalRadiologyResultDao;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.dto.InternalRadiologyResultDto;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.dto.InternalRadiologyResultDtoMapper;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.entity.InternalRadiologyResult;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.request.CreateInternalRadiologyResultRequest;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
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
import java.util.stream.Collectors;

@Service
public class InternalRadiologyResultService {

    private final InternalRadiologyResultDao internalRadiologyResultDao;

    private final InternalRadiologyResultDtoMapper mapper;

    private final FileService fileService;
    private final InternalRadiologyTestDao internalRadiologyTestDao;

    private final IPatientRadiologyExpanseResultTableAddServices patientRadiologyExpanseResultTableAddServices;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public InternalRadiologyResultService(
            @Qualifier("internalRadiologyResultRepo") InternalRadiologyResultDao internalRadiologyResultDao,
            InternalRadiologyResultDtoMapper mapper,
            FileService fileService,
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao,
            @Qualifier("PatientRadiologyExpanseResultTableAddServicesImp") IPatientRadiologyExpanseResultTableAddServices patientRadiologyExpanseResultTableAddServices, ExceptionsMessageReturn messageReturn) {
        this.internalRadiologyResultDao = internalRadiologyResultDao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.internalRadiologyTestDao = internalRadiologyTestDao;
        this.patientRadiologyExpanseResultTableAddServices = patientRadiologyExpanseResultTableAddServices;
        this.messageReturn = messageReturn;
    }


    public void createResult(CreateInternalRadiologyResultRequest request) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        InternalRadiologyTest test = internalRadiologyTestDao.getInternalRadiologyTestById(request.getTestId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("InternalRadiologyTestNotFound") + " " + request.getTestId()
                )
        );
        if (test.getState() != RadiologyOrderState.Accepted) {
            throw new BadRequestException("cannot add result the state is not Accepted");
        }
        InternalRadiologyResult result = new InternalRadiologyResult(request.getNote());
        result.setInternalRadiologyTest(test);


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

        internalRadiologyResultDao.createNewResult(result);
        test.setState(RadiologyOrderState.Completed);

        internalRadiologyTestDao.updateInternalRadiologyTest(test);
        patientRadiologyExpanseResultTableAddServices.addPatientRadiologyExpanseResultTable(test.getPatient().getId());
    }


    public InternalRadiologyResultDto getResultByTestId(Long testId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        internalRadiologyTestDao.getInternalRadiologyTestById(testId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("InternalRadiologyTestNotFound") + " " + testId
                        )
                );

        return internalRadiologyResultDao.getByTestId(testId)
                .map(mapper)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("InternalRadiologyResultNotFound"
                                ) + " " + testId)
                );

    }


    public List<InternalRadiologyResultDto> getAllResultsByPatientIdAndType(Long patientId, RadiologyTypeEnum radiologyType) {
        return internalRadiologyResultDao.getAllResultsByPatientId(patientId, radiologyType)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

}
