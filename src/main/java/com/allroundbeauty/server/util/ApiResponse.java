package com.allroundbeauty.server.util;

import com.allroundbeauty.server.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private int statusCode;
    private T data;
    private String message;

    @Builder
    private ApiResponse(int statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public static  <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(200, data, "요청에 성공하였습니다.");
    }

    public static <T> ApiResponse<T> error(ErrorResponse errorResponse) {
        return new ApiResponse<>(errorResponse.getStatusCode(), null, errorResponse.getErrorMessage());
    }
}