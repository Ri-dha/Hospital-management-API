package com.azu.hospital.ultrasound.internal.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.service.IPatientUltrasoundExpanseResultTableAddServices;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDtoMapper;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class InternalUltrasoundTestStateService extends GenericBaseService {
    private final InternalUltrasoundTestDao internalUltrasoundTestDao;

    private final PatientDao patientDao;
    private final InternalUltrasoundTestDtoMapper mapper;

    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;

    private final IPatientUltrasoundExpanseResultTableAddServices patientUltrasoundExpanseResultTableAddServices;


    public InternalUltrasoundTestStateService(
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            InternalUltrasoundTestDtoMapper mapper,
            JwtService jwtService,
            HttpServletRequest request,
            BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn,
            @Qualifier("PatientUltrasoundExpanseResultTableAddServicesImp") IPatientUltrasoundExpanseResultTableAddServices patientUltrasoundExpanseResultTableAddServices) {
        super(jwtService, request);
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
        this.patientUltrasoundExpanseResultTableAddServices = patientUltrasoundExpanseResultTableAddServices;
    }




    public StatusDto<UltrasoundOrderState> acceptRejectOrder(Long id , String state){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        InternalUltrasoundTest internalUltrasoundTest = internalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if ((internalUltrasoundTest.getState() != UltrasoundOrderState.Waitting)
        && (internalUltrasoundTest.getState() != UltrasoundOrderState.WaittingForDoctorAccept)){
            if (state.equals("reject") || (state.equals("Reject"))){
                throw new BadRequestException(
                        messages.getString("cannotAccept" )+"or"+ messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept" )+"or"+ messages.getString("alreadyExist")

            );
        }
        if(authRoles().contains("DOCTOR")){
            if(state.equals("reject")|| (state.equals("Reject"))){
                internalUltrasoundTest.setState(UltrasoundOrderState.Rejected);
                internalUltrasoundTest.setRejecter(baseUserDao.findById(authId()).orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                ));
                internalUltrasoundTestDao.updateInternalUltrasoundTest(internalUltrasoundTest);
                return new StatusDto<>(UltrasoundOrderState.Rejected);
            } else if (state.equals("accept") || state.equals("Accept")) {
                internalUltrasoundTest.setState(UltrasoundOrderState.Accepted);
                internalUltrasoundTest.setAccepter(baseUserDao.findById(authId()).orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                ));
                internalUltrasoundTestDao.updateInternalUltrasoundTest(internalUltrasoundTest);
                return new StatusDto<>(UltrasoundOrderState.Accepted);
            }
        }
        throw new BadRequestException(
                messages.getString("cannotAccept" )
        );

    }


    public StatusDto<UltrasoundOrderState> cancelAcceptDoctorOrder(Long id , String state){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        InternalUltrasoundTest internalUltrasoundTest = internalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if ((internalUltrasoundTest.getState() != UltrasoundOrderState.WaittingForDoctorAccept)
        && (internalUltrasoundTest.getState() != UltrasoundOrderState.Waitting)) {
            if (state.equals("cancel") || state.equals("Cancel")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept" )+"or"+ messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept" )
            );
        }
        if (authRoles().contains("DOCTOR") ||
                (!internalUltrasoundTest.getUploader().getRoles().contains("DOCTOR") && authRoles().contains("PERMANENT"))
        ){
            if (state.equals("cancel") || state.equals("Cancel")) {
                internalUltrasoundTest.setState(UltrasoundOrderState.DoctorCancel);
                internalUltrasoundTest.setRejecter(baseUserDao.findById(authId()).orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                ));
                internalUltrasoundTestDao.updateInternalUltrasoundTest(internalUltrasoundTest);
                return new StatusDto<>(UltrasoundOrderState.DoctorCancel);
            } else if (state.equals("waitting") || state.equals("Waitting")) {
                internalUltrasoundTest.setState(UltrasoundOrderState.Waitting);
                internalUltrasoundTest.setRejecter(baseUserDao.findById(authId()).orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                ));
                internalUltrasoundTestDao.updateInternalUltrasoundTest(internalUltrasoundTest);
                return new StatusDto<>(UltrasoundOrderState.Waitting);
            }
        }
        throw new BadRequestException(
                messages.getString("cannotAccept")
        );

    }


    public void receivedOrder(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        InternalUltrasoundTest internalUltrasoundTest = internalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (internalUltrasoundTest.getState() != UltrasoundOrderState.Accepted){
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }

        internalUltrasoundTest.setState(UltrasoundOrderState.Received);

        internalUltrasoundTestDao.updateInternalUltrasoundTest(internalUltrasoundTest);

    }

    public void completeOrder(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        InternalUltrasoundTest internalUltrasoundTest = internalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );

        if (internalUltrasoundTest.getState() != UltrasoundOrderState.Received){
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }

        internalUltrasoundTest.setState(UltrasoundOrderState.Completed);

        internalUltrasoundTestDao.updateInternalUltrasoundTest(internalUltrasoundTest);



    }

}
