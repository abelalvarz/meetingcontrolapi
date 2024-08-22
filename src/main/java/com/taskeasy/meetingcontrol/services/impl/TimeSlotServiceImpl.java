package com.taskeasy.meetingcontrol.services.impl;

import com.taskeasy.meetingcontrol.dtos.AvailableTimeUserDTO;
import com.taskeasy.meetingcontrol.dtos.BusyTimeUserDTO;
import com.taskeasy.meetingcontrol.entities.TimeSlot;
import com.taskeasy.meetingcontrol.entities.Meeting;
import com.taskeasy.meetingcontrol.entities.User;
import com.taskeasy.meetingcontrol.repositories.TimeSlotRepository;
import com.taskeasy.meetingcontrol.services.MeetingService;
import com.taskeasy.meetingcontrol.services.TimeSlotService;
import com.taskeasy.meetingcontrol.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private final UserService userService;
    private final MeetingService meetingService;
    private final TimeSlotRepository timeSlotRepository;
    @Override
    public List<AvailableTimeUserDTO> getAvailableTimeUser() {

        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        List<User> users = userService.findAllUser();
        List<AvailableTimeUserDTO> availableTimeUser = new ArrayList<>();

        for (TimeSlot timeSlot : timeSlots){
            List<User> filteredUsers = users.stream()
                    .filter(user -> !hasUserMeeting(user,timeSlot))
                    .collect(Collectors.toList());
            if(filteredUsers.size()>=3)
                availableTimeUser.add(AvailableTimeUserDTO.builder()
                        .slot(timeSlot.getSlot())
                        .availableUsers(filteredUsers)
                        .build());
        }

        return availableTimeUser;
    }

    @Override
    public List<BusyTimeUserDTO> getBusyTimeUser(){

        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        List<User> users = userService.findAllUser();
        List<BusyTimeUserDTO> busyTimeUser = new ArrayList<>();

        for (TimeSlot timeSlot : timeSlots){
            List<User> filteredUsers = users.stream()
                    .filter(user -> hasUserMeeting(user,timeSlot))
                    .collect(Collectors.toList());
            if(!filteredUsers.isEmpty())
                busyTimeUser.add(BusyTimeUserDTO.builder()
                        .slot(timeSlot.getSlot())
                        .userOnMeeting(filteredUsers)
                        .build());
        }
        return busyTimeUser;
    }

    @Override
    public List<TimeSlot> getAllSlots() {
        return timeSlotRepository.findAll();
    }

    private boolean hasUserMeeting(User user, TimeSlot timeSlot){
        Optional<Meeting> meeting = meetingService.findByUserAndSlotId(user.getId(),timeSlot.getId());
        return meeting.isPresent();
    }
}
