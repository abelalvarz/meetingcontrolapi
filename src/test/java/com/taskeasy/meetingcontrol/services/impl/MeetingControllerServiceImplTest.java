package com.taskeasy.meetingcontrol.services.impl;

import com.taskeasy.meetingcontrol.entities.TimeSlot;
import com.taskeasy.meetingcontrol.entities.Meeting;
import com.taskeasy.meetingcontrol.entities.User;
import com.taskeasy.meetingcontrol.repositories.MeetingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MeetingControllerServiceImplTest {

    @Mock
    private MeetingRepository meetingRepository;

    @InjectMocks
    private MeetingServiceImpl timeSlotAssignService;


    @Test
    void findByUserAndSlotId() {
        TimeSlot timeSlot = TimeSlot.builder()
                .id(1L)
                .slot("8:00 AM")
                .build();
        User user = User.builder()
                .id(1L)
                .name("test")
                .build();
        Meeting meeting = Meeting.builder()
                .id(1L)
                .timeSlot(timeSlot)
                .user(user)
                .build();

        Mockito.when(meetingRepository.findByUserIdAndSlotId(1L,1L)).thenReturn(Optional.ofNullable(meeting));

        Optional<Meeting> expectedResult = timeSlotAssignService.findByUserAndSlotId(1L,1L);
        assertTrue(expectedResult.isPresent());
        assertEquals("8:00 AM",expectedResult.get().getTimeSlot().getSlot());
    }
}