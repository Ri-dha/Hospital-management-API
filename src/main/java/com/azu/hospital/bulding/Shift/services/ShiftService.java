package com.azu.hospital.bulding.Shift.services;

import com.azu.hospital.bulding.Shift.dao.ShiftDao;
import com.azu.hospital.bulding.Shift.dto.ShiftDto;
import com.azu.hospital.bulding.Shift.dto.ShiftDtoMapper;
import com.azu.hospital.bulding.Shift.entity.Shift;
import com.azu.hospital.bulding.Shift.request.ShiftAddRequest;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ShiftService extends GenericBaseService {

    private final ShiftDao shiftDao;

    private final ShiftDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public ShiftService(
            JwtService jwtService,
            HttpServletRequest request,
            @Qualifier("ShiftDataJpa") ShiftDao shiftDao,
            ShiftDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        super(jwtService, request);
        this.shiftDao = shiftDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public void createShift(ShiftAddRequest request) {

        Shift shift = new Shift(
                request.name(),
                request.fromTime(),
                request.toTime() ,
                request.type()
        );

        shiftDao.createNewShift(shift);

    }

    public void updateShift(ShiftAddRequest request, Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Shift shift = shiftDao.getShiftById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        Boolean changed = false;


        if (request.name() != null && !request.name().equals(shift.getName())) {
            shift.setName(request.name());
            changed = true;
        }
        if (request.fromTime() != null && !request.fromTime().equals(shift.getFromTime())) {
            shift.setFromTime(request.fromTime());
            changed = true;
        }
        if (request.toTime() != null && !request.toTime().equals(shift.getToTime())) {
            shift.setToTime(request.toTime());
            changed = true;
        }

        if (!changed) {
            throw new BadRequestException(
                    messages.getString("noChange")
            );
        }

        shift.setName(request.name());
        shift.setFromTime(request.fromTime());
        shift.setToTime(request.toTime());

        shiftDao.updateShift(shift);

    }

    public ShiftDto getShiftById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Shift shift = shiftDao.getShiftById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return mapper.apply(shift);
    }

    public Page<ShiftDto> getAllShifts(Pageable pageable) {
        return shiftDao.getAllShifts(pageable).map(mapper);
    }



}
