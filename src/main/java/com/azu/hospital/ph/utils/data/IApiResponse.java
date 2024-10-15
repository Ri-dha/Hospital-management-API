package com.azu.hospital.ph.utils.data;

public interface IApiResponse<T> {
    String getMessage();
    boolean getStatus();
    int getStatusCode();
    T getData();
}
