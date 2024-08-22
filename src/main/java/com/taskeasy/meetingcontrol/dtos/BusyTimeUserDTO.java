package com.taskeasy.meetingcontrol.dtos;

import com.taskeasy.meetingcontrol.entities.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BusyTimeUserDTO {
    private String slot;
    private List<User> userOnMeeting;
}
