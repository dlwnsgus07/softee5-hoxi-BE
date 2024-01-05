package com.allroundbeauty.server.util;

import com.allroundbeauty.server.exception.ErrorResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof ErrorResponse errorResponse){
            return ApiResponse.builder()
                    .statusCode(errorResponse.getStatusCode())
                    .message(errorResponse.getErrorMessage())
                    .data(null)
                    .build();
        }
        return ApiResponse.builder()
                .statusCode(200)
                .message("정상 응답입니다.")
                .data(body).build();
    }
}
