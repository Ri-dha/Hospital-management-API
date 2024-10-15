package com.azu.hospital.books.copyTo.dto;

import com.azu.hospital.books.copyTo.entity.CopyTo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CopyToDtoMapper implements Function<CopyTo, CopyToDto> {
    @Override
    public CopyToDto apply(CopyTo copyTo) {
        return new CopyToDto(
                copyTo.getId(),
                copyTo.getDepartment() == null ? null : copyTo.getDepartment().getDepartmentName(),
                copyTo.getCreatedAt()
        );
    }
}
