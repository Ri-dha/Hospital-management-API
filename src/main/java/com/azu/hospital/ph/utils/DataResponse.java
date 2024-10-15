package com.azu.hospital.ph.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponse<T> {
    private T data;
    private String message;
    private int status;
    private PaginationInfo paginationInfo;
}