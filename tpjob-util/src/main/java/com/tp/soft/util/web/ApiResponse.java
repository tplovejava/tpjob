package com.tp.soft.util.web;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
    private final static String STATUS_SUCCESS = "success";
    private final static String STATUS_FAILURE = "failure";
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ApiResponse<T> success(Object data) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setCode(STATUS_SUCCESS);
        apiResponse.setData((T) data);
        return apiResponse;
    }

    public static ApiResponse<Void> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> failure(String message,Object data) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setCode(STATUS_FAILURE);
        apiResponse.setMessage(message);
        apiResponse.setData((T) data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> failure(Object data) {
        return failure(null,data);
    }

    public static ApiResponse<Void> failure() {
        return failure(null);
    }
}
