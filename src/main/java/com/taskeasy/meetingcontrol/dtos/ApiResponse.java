package com.taskeasy.meetingcontrol.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private boolean isSuccess;
    private int statusCode;
    private String message;
    private Object data;
}
