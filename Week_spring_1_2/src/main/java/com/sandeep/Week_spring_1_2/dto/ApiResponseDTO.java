package com.sandeep.Week_spring_1_2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDTO<T> {

    private boolean success;
    private String message;
    private T data;

    public ApiResponseDTO(boolean b, String message, T data) {
        this.success=b;
        this.message=message;
        this.data=data;
    }


    public static <T> ApiResponseDTO<T> success(String message, T data) {
        return new ApiResponseDTO<>(true, message, data);
    }

    public static <T> ApiResponseDTO<T> failure(String message) {
        return new ApiResponseDTO<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }




}
