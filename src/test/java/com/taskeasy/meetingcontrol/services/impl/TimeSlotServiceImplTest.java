package com.taskeasy.meetingcontrol.services.impl;

import com.taskeasy.meetingcontrol.dtos.AvailableTimeUserDTO;
import com.taskeasy.meetingcontrol.dtos.BusyTimeUserDTO;
import com.taskeasy.meetingcontrol.entities.TimeSlot;
import com.taskeasy.meetingcontrol.entities.Meeting;
import com.taskeasy.meetingcontrol.entities.User;
import com.taskeasy.meetingcontrol.repositories.MeetingRepository;
import com.taskeasy.meetingcontrol.repositories.TimeSlotRepository;
import com.taskeasy.meetingcontrol.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TimeSlotServiceImplTest {

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MeetingRepository meetingRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private MeetingServiceImpl meetingService;


    @Test
    void getAllSlots() {
        List<TimeSlot> timeSlotList = getTimeSlots();
        Mockito.when(timeSlotRepository.findAll()).thenReturn(timeSlotList);
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        assertEquals(3, timeSlots.size());
    }

    @Test
    void getAvailableTimeUser() {
        List<TimeSlot> timeSlotList = getTimeSlots();
        List<User> userList = getAllUser();

        Mockito.when(timeSlotRepository.findAll()).thenReturn(timeSlotList);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(meetingRepository.findByUserIdAndSlotId(1L,1L)).thenReturn(Optional.empty());

        List<AvailableTimeUserDTO> timeSlotAvailable = getAllSlotAvailableWithUser();

        assertEquals(3, timeSlotAvailable.size());
        assertEquals("8:00 AM", timeSlotAvailable.get(0).getSlot());
        assertEquals("kyle", timeSlotAvailable.get(0).getAvailableUsers().get(0).getName());
        assertEquals("Luis", timeSlotAvailable.get(0).getAvailableUsers().get(1).getName());
        assertEquals("Paul", timeSlotAvailable.get(0).getAvailableUsers().get(2).getName());

        assertEquals("9:00 AM", timeSlotAvailable.get(1).getSlot());
        assertEquals("kyle", timeSlotAvailable.get(1).getAvailableUsers().get(0).getName());
        assertEquals("Luis", timeSlotAvailable.get(1).getAvailableUsers().get(1).getName());
        assertEquals("Paul", timeSlotAvailable.get(1).getAvailableUsers().get(2).getName());

        assertEquals("10:00 AM", timeSlotAvailable.get(2).getSlot());
        assertEquals("kyle", timeSlotAvailable.get(2).getAvailableUsers().get(0).getName());
        assertEquals("Luis", timeSlotAvailable.get(2).getAvailableUsers().get(1).getName());
        assertEquals("Paul", timeSlotAvailable.get(2).getAvailableUsers().get(2).getName());
    }

    @Test
    void getBusyTimeUser() {
        List<TimeSlot> timeSlotList = getTimeSlots();
        List<User> userList = getAllUser();
        List<Meeting> meetings = getAllMeeting(timeSlotList, userList);

        Mockito.when(timeSlotRepository.findAll()).thenReturn(timeSlotList);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(meetingRepository.findByUserIdAndSlotId(1L,1L)).thenReturn(Optional.ofNullable(meetings.get(0)));

        List<BusyTimeUserDTO> timeSlotAvailable = getAllSlotBusyWithUser();

        assertEquals(1, timeSlotAvailable.size());
        assertEquals("8:00 AM", timeSlotAvailable.get(0).getSlot());
        assertEquals("kyle", timeSlotAvailable.get(0).getUserOnMeeting().get(0).getName());

    }

    private List<TimeSlot> getTimeSlots(){
        List<TimeSlot> timeSlotList = new ArrayList<>();
        timeSlotList.add(TimeSlot.builder().id(1L).slot("8:00 AM").build());
        timeSlotList.add(TimeSlot.builder().id(2L).slot("9:00 AM").build());
        timeSlotList.add(TimeSlot.builder().id(3L).slot("10:00 AM").build());
        return timeSlotList;
    }
    private List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().id(1L).name("kyle").build());
        userList.add(User.builder().id(2L).name("Luis").build());
        userList.add(User.builder().id(3L).name("Paul").build());
        return userList;
    }

    private List<Meeting> getAllMeeting(List<TimeSlot> timeSlot, List<User> userList){
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(Meeting.builder().id(1L).timeSlot(timeSlot.get(0)).user(userList.get(0)).build());
        meetings.add(Meeting.builder().id(2L).timeSlot(timeSlot.get(1)).user(userList.get(1)).build());
        return meetings;
    }
    private List<AvailableTimeUserDTO> getAllSlotAvailableWithUser(){
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        List<User> users = userService.findAllUser();
        List<AvailableTimeUserDTO> availableTimeUserDTOS = new ArrayList<>();

        for(TimeSlot time : timeSlots){
            List<User> availableUser = users.stream()
                    .filter(user-> !hasUserMeeting(user, time))
                    .collect(Collectors.toList());

            if(availableUser.size()>=3)
                availableTimeUserDTOS.add(AvailableTimeUserDTO.builder().availableUsers(availableUser).slot(time.getSlot()).build());
        }
        return availableTimeUserDTOS;
    }
    private List<BusyTimeUserDTO> getAllSlotBusyWithUser(){
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();
        List<User> users = userService.findAllUser();
        List<BusyTimeUserDTO> busyTimeUsers = new ArrayList<>();

        for(TimeSlot time : timeSlots){
            List<User> busyUsers = users.stream()
                    .filter(user -> hasUserMeeting(user, time) )
                    .collect(Collectors.toList());

            if(!busyUsers.isEmpty())
                busyTimeUsers.add(BusyTimeUserDTO.builder().userOnMeeting(busyUsers).slot(time.getSlot()).build());
        }
        return busyTimeUsers;
    }

    private boolean hasUserMeeting(User user, TimeSlot timeSlot){
        Optional<Meeting> meeting = meetingService.findByUserAndSlotId(user.getId(),timeSlot.getId());
        return meeting.isPresent();
    }

}