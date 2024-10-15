package com.azu.hospital.accounting.insurance.dto;


import com.azu.hospital.accounting.insurance.entity.Insurance;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InsuranceDtoMapper implements Function<Insurance, InsuranceDto> {
    @Override
    public InsuranceDto apply(Insurance insurance) {
        return new InsuranceDto(
                insurance.getId(),
                insurance.getInsuranceAmount(),
                insurance.getDepositAmount()
        );
    }
}
