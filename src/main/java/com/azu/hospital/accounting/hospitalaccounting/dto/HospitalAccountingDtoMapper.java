package com.azu.hospital.accounting.hospitalaccounting.dto;

import com.azu.hospital.accounting.hospitalaccounting.entity.HospitalAccounting;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class HospitalAccountingDtoMapper implements Function<HospitalAccounting, HospitalAccountingDto> {

    private final BaseUserDao baseUserDao;

    @Autowired
    public HospitalAccountingDtoMapper(BaseUserDao baseUserDao) {
        this.baseUserDao = baseUserDao;
    }

    @Override
    public HospitalAccountingDto apply(HospitalAccounting hospitalAccounting) {
        if (hospitalAccounting == null) {
            return null;
        }


        String createdByUsername = getNameById(hospitalAccounting.getCreatedBy());


        String lastModifiedByUsername = getNameById(hospitalAccounting.getLastModifiedBy());

        return new HospitalAccountingDto(
                hospitalAccounting.getId(),
                hospitalAccounting.getOperationName(),
                hospitalAccounting.getOperationId(),
                hospitalAccounting.getPatientId(),
                hospitalAccounting.getHospitalIncome(),
                hospitalAccounting.getOperationCost(),
                hospitalAccounting.getPatientName(),
                hospitalAccounting.getDate(),
                hospitalAccounting.getCreatedBy(),
                createdByUsername,
                hospitalAccounting.getLastModifiedBy(),
                lastModifiedByUsername,
                hospitalAccounting.getCreateAt(),
                hospitalAccounting.getUpdateAt()
        );
    }

    private String getNameById(Long userId) {
        return baseUserDao.findById(userId)
                .map(BaseUser::getUsernameSpecial)
                .orElse(null);
    }
}
