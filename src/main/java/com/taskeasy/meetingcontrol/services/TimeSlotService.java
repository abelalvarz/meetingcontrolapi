package com.taskeasy.meetingcontrol.services;

import com.taskeasy.meetingcontrol.dtos.AvailableTimeUserDTO;
import com.taskeasy.meetingcontrol.dtos.BusyTimeUserDTO;
import com.taskeasy.meetingcontrol.entities.TimeSlot;

import java.util.List;

public interface TimeSlotService {

    List<AvailableTimeUserDTO> getAvailableTimeUser();
    List<BusyTimeUserDTO> getBusyTimeUser();
    List<TimeSlot> getAllSlots();
}
