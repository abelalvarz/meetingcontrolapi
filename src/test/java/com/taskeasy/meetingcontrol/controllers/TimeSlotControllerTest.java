package com.taskeasy.meetingcontrol.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class TimeSlotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllAvailableTimes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/times/available"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllBusyTimes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/times/busy"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllSlotTimes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/times/slots"))
                .andExpect(status().isOk());
    }
}