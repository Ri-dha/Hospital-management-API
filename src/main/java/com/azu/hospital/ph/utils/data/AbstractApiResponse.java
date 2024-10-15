package com.azu.hospital.ph.utils.data;

public abstract class AbstractApiResponse<T> implements IApiResponse<T> {

    private String message;
    private boolean status;
    private int statusCode;
    private T data;


    public AbstractApiResponse(String message, boolean status, int statusCode, T data) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }

    public AbstractApiResponse() {

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public T getData() {
        return data;
    }
}
