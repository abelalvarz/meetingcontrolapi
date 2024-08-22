package com.taskeasy.meetingcontrol.controllers;

import com.taskeasy.meetingcontrol.converters.ResponseConverter;
import com.taskeasy.meetingcontrol.dtos.ApiResponse;
import com.taskeasy.meetingcontrol.services.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/times")
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotService timeSlotService;
    private final ResponseConverter responseConverter;

    @GetMapping("/available")
    public ResponseEntity<ApiResponse> getAllAvailableTimes(){
        return ResponseEntity.ok(responseConverter.convert(timeSlotService.getAvailableTimeUser()));
    }
    @GetMapping("/busy")
    public ResponseEntity<ApiResponse> getAllBusyTimes(){
        return ResponseEntity.ok(responseConverter.convert(timeSlotService.getBusyTimeUser()));
    }

    @GetMapping("/slots")
    public ResponseEntity<ApiResponse> getAllSlotTimes(){
        return ResponseEntity.ok(responseConverter.convert(timeSlotService.getAllSlots()));
    }

}
