package com.azu.hospital.ecg.internal.ecgInternalResults.Services;


import com.azu.hospital.accounting.all_item_expanse.ecg.service.IPatientEcgExpanseResultTableAddServices;
import com.azu.hospital.ecg.internal.dao.EcgDao;
import com.azu.hospital.ecg.internal.ecgInternalResults.dao.InternalEcgResultDao;
import com.azu.hospital.ecg.internal.ecgInternalResults.dto.InternalEcgDto;
import com.azu.hospital.ecg.internal.ecgInternalResults.dto.InternalEcgMapper;
import com.azu.hospital.ecg.internal.ecgInternalResults.entity.InternalEcgResult;
import com.azu.hospital.ecg.internal.ecgInternalResults.request.CreateInternalEcgResultRequest;
import com.azu.hospital.ecg.internal.ecgInternalResults.request.UpdateInternalEcgResultRequest;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("internalEcgResultService")
public class InternalEcgResultService {

    private final InternalEcgResultDao internalEcgResultDao;
    private final InternalEcgMapper internalEcgMapper;
    private final FileService fileService;
    private final EcgDao ecgDao;

    private final IPatientEcgExpanseResultTableAddServices patientEcgExpanseResultTableAddServices;

    @Autowired
    public InternalEcgResultService(
            @Qualifier("internalEcgResultRepo") InternalEcgResultDao internalEcgResultDao,
            @Qualifier("internalEcgMapper") InternalEcgMapper internalEcgMapper,
            @Qualifier("fileService") FileService fileService,
            @Qualifier("ecgJpa") EcgDao ecgDao,
            @Qualifier("PatientEcgExpanseResultTableAddServicesImp") IPatientEcgExpanseResultTableAddServices patientEcgExpanseResultTableAddServices) {
        this.internalEcgResultDao = internalEcgResultDao;
        this.internalEcgMapper = internalEcgMapper;
        this.fileService = fileService;
        this.ecgDao = ecgDao;
        this.patientEcgExpanseResultTableAddServices = patientEcgExpanseResultTableAddServices;
    }


    public InternalEcgDto createResult(CreateInternalEcgResultRequest request) throws IOException{
        Ecg test = ecgDao.findTestById(request.getTestId()).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );


        InternalEcgResult result = new InternalEcgResult(request.getNote());

        result.setEcg(test);

        if (request.getFiles() != null && !request.getFiles().isEmpty()){
            List<File> fileList = new ArrayList<>();

            for(MultipartFile files: request.getFiles()){
                if (!files.isEmpty()){
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType() , "Ecg" ,url );
                    fileList.add(file);
                }
            }
            result.setFiles(fileList);
        }


        internalEcgResultDao.createNewResult(result);
        test.setState(EcgStates.Completed);
        ecgDao.updateEcgTest(test);

        patientEcgExpanseResultTableAddServices.addPatientEcgExpanseResultTable(test.getPatient().getId(),test);

        return internalEcgMapper.apply(result);
    }



    public Optional<InternalEcgDto> getResultByTestId(Long testId){

        Ecg test = ecgDao.findTestById(testId).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );
        return internalEcgResultDao.getByTestId(testId).map(internalEcgMapper);

    }


    public InternalEcgDto updateResult(Long testId,UpdateInternalEcgResultRequest request) throws IOException {

        InternalEcgResult result = internalEcgResultDao.getByTestId(testId).orElseThrow(
                () -> new ResourceNotFoundException("Result Doesn't Exists")
        );

        result.setNote(
                request.getNote() == null ? result.getNote() : request.getNote()
        );

        if (request.getFiles() != null && !request.getFiles().isEmpty()){
            List<File> fileList = new ArrayList<>();

            for(MultipartFile files: request.getFiles()){
                if (!files.isEmpty()){
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType() , "Ecg" ,url );
                    fileList.add(file);
                }
            }
            result.setFiles(fileList);
        }

        internalEcgResultDao.updateResult(result);

        return internalEcgMapper.apply(result);

    }

}
