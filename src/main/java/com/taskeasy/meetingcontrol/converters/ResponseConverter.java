package com.taskeasy.meetingcontrol.converters;

import com.taskeasy.meetingcontrol.dtos.ApiResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter implements Converter<Object, ApiResponse> {
    @Override
    public ApiResponse convert(Object source) {
        return ApiResponse.builder()
                .isSuccess(true)
                .statusCode(0)
                .message("Success Operation")
                .data(source)
                .build();
    }
}
