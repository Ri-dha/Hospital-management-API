package com.azu.hospital.operation.service;

import com.azu.hospital.accounting.all_item_expanse.opreation.service.IPatientOperationExpanseResultTableAddService;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.notifications.SseServer.ISseService;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.operation.dto.OperationMapper;
import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.operation.OperationStates;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OperationStateService extends GenericBaseService {

    private final OperationDao operationDao;
    private final BaseUserDao baseUserDao;
    private final ISseService sseService;
    private final OperationMapper mapper;
    private final ExceptionsMessageReturn messageReturn;
//    private final RSocketServer rSocketServer;


    private final IPatientOperationExpanseResultTableAddService patientOperationExpanseResultTableAddService;

    public OperationStateService(
            JwtService jwtService,
            HttpServletRequest request,
            OperationDao operationDao,
            BaseUserDao baseUserDao,
            ISseService sseService,
            OperationMapper mapper,
            ExceptionsMessageReturn messageReturn,
            @Qualifier("PatientOperationExpanseResultTableAddServiceImp") IPatientOperationExpanseResultTableAddService patientOperationExpanseResultTableAddService
    ) {
        super(jwtService, request);
        this.operationDao = operationDao;
        this.baseUserDao = baseUserDao;
        this.sseService = sseService;
        this.mapper = mapper;

        this.messageReturn = messageReturn;
        this.patientOperationExpanseResultTableAddService = patientOperationExpanseResultTableAddService;
    }


    @Transactional
    public StatusDto<OperationStates> acceptOrRejectOrder(Long id, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Operation operation = operationDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("operationNotFound")
                                + " " + id
                )
        );
        if ((operation.getState() != OperationStates.InWard)) {
            if (state.equals("reject") || state.equals("Reject")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
                );

            }
            throw new BadRequestException(
                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
            );

        }

        if (state.equals("reject") || state.equals("Reject")) {
            operation.setState(OperationStates.Rejected);
            operation.setRejecter(baseUserDao.findById(authId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("userNotFound") + " " + authId()
                    )));
            operationDao.updateOperation(operation);
            return new StatusDto<>(OperationStates.Rejected);
        } else if (state.equals("waiting") || state.equals("Waiting")) {
            operation.setState(OperationStates.Waiting);
            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("userNotFound") + " " + authId()
                    )));
            operationDao.updateOperation(operation);
            return new StatusDto<>(OperationStates.Waiting);
        }

        throw new BadRequestException(
                messages.getString("cannotAccept")
        );
    }


    public StatusDto<OperationStates> cancelOrder(Long id, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Operation operation = operationDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("operationNotFound") + " " + id
                )
        );
        if ((operation.getState() != OperationStates.Waiting)) {
            if (state.equals("cancel") || state.equals("Cancel")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

            );
        }
        if (authRoles().contains("DOCTOR")) {
            if (state.equals("cancel") || state.equals("Cancel")) {
                operation.setState(OperationStates.Canceled);
                operation.setRejecter(baseUserDao.findById(authId()).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("userNotFound") + " " + authId()
                        )
                ));
                operationDao.updateOperation(operation);

                return new StatusDto<>(OperationStates.Canceled);
            }
        }
        throw new BadRequestException(
                messages.getString("cannotAccept")
        );

    }

    public StatusDto<OperationStates> inOperationDoctorOrder(Long id, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Operation operation = operationDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("operationNotFound") + " " + id
                )
        );
        if ((operation.getState() != OperationStates.Waiting)) {
            if (state.equals("inOperation") || state.equals("InOperation")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

            );

        }

        if (state.equals("inOperation") || state.equals("InOperation")) {
            operation.setState(OperationStates.InOperation);
            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("userNotFound") + " " + authId()
                    )
            ));
            operationDao.updateOperation(operation);

//                SendNotificationDto<OperationDto> sendNotificationDto = new SendNotificationDto(
//                        "Patient is in operation room", "Patient " + operation.getPatient() + "is in operation room", mapper.apply(operation), Instant.now()
//                );
//
//                SseClient manager = sseService.registeredClientById(operation.getPatient().getWard().getManager().getId());
//                sseService.sendMessage(manager , DestinationEnum.OPERATION ,sendNotificationDto);
//                SseClient managerAssistance = sseService.registeredClientById(operation.getPatient().getWard().getManagerAssistance().getId());
//                sseService.sendMessage(managerAssistance, DestinationEnum.OPERATION ,sendNotificationDto);
            return new StatusDto<>(OperationStates.InOperation);
        }
        throw new BadRequestException(
                messages.getString("cannotAccept")
        );

    }

    public StatusDto<OperationStates> beforeRecovery(Long id, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Operation operation = operationDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("operationNotFound") + " " + id
                )
        );
        if ((operation.getState() != OperationStates.InOperation)) {
            if (state.equals("beforeRecover") || state.equals("BeforeRecover")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

            );
        }

        if (state.equals("beforeRecover") || (state.equals("BeforeRecover"))) {
            operation.setState(OperationStates.BeforeRecover);
            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("userNotFound")+ " " + authId()
                    )
            ));
            operationDao.updateOperation(operation);
            patientOperationExpanseResultTableAddService.addPatientOperationExpanseResultTable(operation.getPatient().getId());
            return new StatusDto<>(OperationStates.BeforeRecover);
        }
        throw new BadRequestException(
                messages.getString("cannotAccept")
        );
    }

    public StatusDto<OperationStates> recoveryOrder(Long id, String state) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Operation operation = operationDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("operationNotFound") + " " + id
                )
        );
        if ((operation.getState() != OperationStates.BeforeRecover)) {
            if (state.equals("Recovery") || state.equals("recovery")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

            );
        }

        if (state.equals("Recovery") || state.equals("recovery")) {
            operation.setState(OperationStates.Recovery);
            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("userNotFound") + " " + authId()
                    )
            ));
            operationDao.updateOperation(operation);

            return new StatusDto<>(OperationStates.Recovery);
        }
        throw new BadRequestException(
                messages.getString("cannotAccept")
        );
    }


    public StatusDto<OperationStates> sentToWardOrder(Long id, String state, String doneNote) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Operation operation = operationDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("operationNotFound") + " " + id
                )
        );
        if ((operation.getState() != OperationStates.Recovery)) {
            if (state.equals("SentToWard") || state.equals("sentToWard")) {
                throw new BadRequestException(
                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

                );
            }
            throw new BadRequestException(
                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")

            );
        }
        operation.setDoneNote(doneNote);

        if (state.equals("SentToWard") || state.equals("sentToWard")) {
            operation.setState(OperationStates.PatientReceived);
            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("userNotFound") + " " + authId()
                    )
            ));
            operationDao.updateOperation(operation);
            return new StatusDto<>(OperationStates.PatientReceived);
        }


        throw new BadRequestException(
                messages.getString("cannotAccept")
        );

    }

//    public StatusDto<OperationStates> waitingForWardOrder(Long id, String state) {
//        Locale locale = messageReturn.getMessageLocally();
//        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
//        Operation operation = operationDao.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(
//                        messages.getString("operationNotFound") + " " + id
//                )
//        );
//        if ((operation.getState() != OperationStates.SentToWard)) {
//            if (state.equals("WaitingForWard") || state.equals("waitingForWard")) {
//                throw new BadRequestException(
//                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
//
//                );
//            }
//            throw new BadRequestException(
//                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
//
//            );
//        }
//
//        if (state.equals("WaitingForWard") || state.equals("waitingForWard")) {
//            operation.setState(OperationStates.WaitingForWard);
//            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
//                    () -> new ResourceNotFoundException(
//                            messages.getString("userNotFound") + " " + authId()
//                    )
//            ));
//            operationDao.updateOperation(operation);
//            return new StatusDto<>(OperationStates.WaitingForWard);
//        }
//
//        throw new BadRequestException(
//                messages.getString("cannotAccept")
//        );
//
//    }


//    public StatusDto<OperationStates> inWardOrder(Long id, String state) {
//        Locale locale = messageReturn.getMessageLocally();
//        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
//
//        Operation operation = operationDao.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(
//                        messages.getString("operationNotFound") + " " + id
//                )
//        );
//        if ((operation.getState() != OperationStates.WaitingForWard)) {
//            if (state.equals("InWard") || state.equals("inWard")) {
//                throw new BadRequestException(
//                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
//
//                );
//            }
//            throw new BadRequestException(
//                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
//
//            );
//        }
//
//        if (state.equals("InWard") || state.equals("inWard")) {
//            operation.setState(OperationStates.InWard);
//            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
//                    () -> new ResourceNotFoundException(
//                            messages.getString("userNotFound") + " " + authId()
//                    )
//            ));
//            operationDao.updateOperation(operation);
//            return new StatusDto<>(OperationStates.InWard);
//        }
//
//        throw new BadRequestException(
//                messages.getString("cannotAccept")
//        );
//
//    }

//    public StatusDto<OperationStates> PatientReceivedOrder(Long id, String state) {
//        Locale locale = messageReturn.getMessageLocally();
//        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
//        Operation operation = operationDao.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException(
//                        messages.getString("operationNotFound") + " " + id
//                )
//        );
//        if ((operation.getState() != OperationStates.WaitingForWard)) {
//            if (state.equals("PatientReceived") || state.equals("patientReceived")) {
//                throw new BadRequestException(
//                        messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
//
//                );
//            }
//            throw new BadRequestException(
//                    messages.getString("cannotAccept") + "or" + messages.getString("alreadyExist")
//
//            );
//        }
//
//        if (state.equals("PatientReceived") || state.equals("patientReceived")) {
//            operation.setState(OperationStates.PatientReceived);
//            operation.setAccepter(baseUserDao.findById(authId()).orElseThrow(
//                    () -> new ResourceNotFoundException(
//                            messages.getString("userNotFound") + " " + authId()
//                    )
//            ));
//            operationDao.updateOperation(operation);
//
////            SendNotificationDto<OperationDto> sendNotificationDto = new SendNotificationDto(
////                    "Patient return to ward", "Patient " + operation.getPatient() + "return to ward", mapper.apply(operation), Instant.now()
////            );
////
////            SseClient manager = sseService.registeredClientById(operation.getPatient().getWard().getManager().getId());
////            sseService.sendMessage(manager , DestinationEnum.OPERATION ,sendNotificationDto);
////            SseClient managerAssistance = sseService.registeredClientById(operation.getPatient().getWard().getManagerAssistance().getId());
////            sseService.sendMessage(managerAssistance, DestinationEnum.OPERATION ,sendNotificationDto);
//
//            return new StatusDto<>(OperationStates.InWard);
//        }
//
//        throw new BadRequestException(
//                messages.getString("cannotAccept")
//        );
//
//    }
}
