package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services;

import com.azu.hospital.accounting.all_item_expanse.drugs.services.IPatientDrugsExpanseResultTableAddServices;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.dao.SoldBillDao;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.ph.Sales.solid_item_list.dao.SoldItemDao;
import com.azu.hospital.ph.Sales.solid_item_list.entity.SoldItems;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerStatusRegistry;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class DrugRequestHandlerStatusServices extends GenericBaseService {

    private final DrugRequestHandlerDao handlerDao;
    private final DrugRequestHandlerListDao listDao;
    private final DrugItemDao drugItemDao;

    private final SoldBillDao soldBillDao;
    private final SoldItemDao soldItemDao;

    private final BaseUserDao userDao;

    private final IPatientDrugsExpanseResultTableAddServices patientDrugsExpanseResultTableAddServices;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public DrugRequestHandlerStatusServices(
            @Qualifier("DrugRequestHandlerJpa") DrugRequestHandlerDao handlerDao,
            @Qualifier("DrugRequestHandlerListJpa") DrugRequestHandlerListDao listDao,
            @Qualifier("DrugItemJpa") DrugItemDao drugItemDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest,
            @Qualifier("soldJpa") SoldBillDao soldBillDao, SoldItemDao soldItemDao, BaseUserDao userDao,
            @Qualifier("PatientDrugsExpanseResultTableAddServicesImp") IPatientDrugsExpanseResultTableAddServices patientDrugsExpanseResultTableAddServices,
            ExceptionsMessageReturn messageReturn) {
        super(jwtService, httpServletRequest);
        this.handlerDao = handlerDao;
        this.listDao = listDao;
        this.drugItemDao = drugItemDao;
        this.soldBillDao = soldBillDao;
        this.soldItemDao = soldItemDao;
        this.userDao = userDao;
        this.patientDrugsExpanseResultTableAddServices = patientDrugsExpanseResultTableAddServices;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public ObjectNode acceptOrRejectRequest(Long listId, List<DrugRequestHandlerStatusRegistry> requests) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        DrugRequestHandlerList requestList = listDao.getDrugRequestHandlerListById(listId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        for (DrugRequestHandlerStatusRegistry handler : requests) {
            DrugRequestHandler requestHandler = handlerDao.getRequestById(handler.id())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("resourceNotFound")
                            )
                    );
            if (requestHandler != null) {
                if (requestHandler.getRequestStatus() == UnitInventoryRequestEnum.Cancel) {
                    throw new IllegalStateException(
                            messages.getString("alreadyExist")
                    );
                }

                for (DrugRequestHandlerStatusRegistry request : requests) {
                    if (requestHandler.getRequestId().equals(request.id())
                            && requestHandler.getRequestStatus() == UnitInventoryRequestEnum.Waitting
                            && request.state().equalsIgnoreCase("Accepted")) {
                        requestHandler.setRequestStatus(UnitInventoryRequestEnum.Accepted);
                        handlerDao.updateDrugRequest(requestHandler);
                        json.put("status", "Request Accepted");
                        break;
                    }

                }


            }


        }
        return json;


        // TODO: 10/11/2023 send notifications to the user request for notify him about the request status
        // TODO: 10/11/2023 you should return a cause of reject to now users why rejected  and save it in tables

    }


    public ObjectNode cancelRequest(Long requestId, String status, Integer quantity) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        DrugRequestHandler request = handlerDao.getRequestById(requestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );


        if (quantity == null) {
            if (request.getRequestStatus() != UnitInventoryRequestEnum.Cancel
                    && status.equals("Cancel") || status.equals("cancel")
            ) {
                request.setRequestStatus(UnitInventoryRequestEnum.Cancel);
                json.put("status", "Request Cancel");
                handlerDao.updateDrugRequest(request);

            }
        }
        if (quantity != null) {
            request.setRequestStatus(UnitInventoryRequestEnum.Received);
            request.setQuantity(quantity);
            json.put("status", "Request Cancel only + " + quantity);
            handlerDao.updateDrugRequest(request);
        } else {
            json.put("status", "The status of Request Is already Change, You can't Cancel it");
        }
        return json;
    }

    @Transactional
    public ObjectNode receivedOrNotReceivedTheItemRequest(Long requestId, String status) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        DrugRequestHandler request = handlerDao.getRequestById(requestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )

                );

        if (request.getRequestStatus() == UnitInventoryRequestEnum.Cancel) {
            throw new IllegalStateException(
                    messages.getString("alreadyExist")
            );
        }
        if (request.getRequestStatus() == UnitInventoryRequestEnum.Preparing
                && status.equals("Received") || status.equals("received")
        ) {
            request.setRequestStatus(UnitInventoryRequestEnum.Received);
            handlerDao.updateDrugRequest(request);
            patientDrugsExpanseResultTableAddServices.addPatientDrugsExpanseResultTable(request.getPatient().getId());
            json.put("status", "Request Received");


        } else {
            request.setRequestStatus(UnitInventoryRequestEnum.Not_Received);
            json.put("status", "Request Not Received");
            handlerDao.updateDrugRequest(request);
        }
        return json;
    }


}
