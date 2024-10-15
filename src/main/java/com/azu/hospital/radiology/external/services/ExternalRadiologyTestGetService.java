package com.azu.hospital.radiology.external.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.external.dao.ExternalRadiologyTestDao;
import com.azu.hospital.radiology.external.dto.ExternalRadiologyTestDto;
import com.azu.hospital.radiology.external.dto.ExternalRadiologyTestDtoMapper;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ExternalRadiologyTestGetService extends GenericBaseService {

    private final ExternalRadiologyTestDao externalRadiologyTestDao;

    private final ExternalRadiologyTestDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ExternalRadiologyTestGetService(
            @Qualifier("externalRadiologyTestRepo") ExternalRadiologyTestDao externalRadiologyTestDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest,
            ExternalRadiologyTestDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        super(jwtService, httpServletRequest);
        this.externalRadiologyTestDao = externalRadiologyTestDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }




    public Page<ExternalRadiologyTestDto> getAllTests(
            List<RadiologyTypeEnum> types ,
            List<RadiologyOrderState> states,
            String search,
            Pageable pageable
    ) {

        return externalRadiologyTestDao.getAllByFilter(types , states, search, pageable).map(mapper);

    }

    public ExternalRadiologyTestDto getById(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        ExternalRadiologyTest externalRadiologyTest = externalRadiologyTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("ExternalRadiologyTestNotFound")+" "+id
                )
        );

        return mapper.apply(externalRadiologyTest);
    }



}
