package com.taskeasy.meetingcontrol.services;

import com.taskeasy.meetingcontrol.entities.Meeting;

import java.util.List;
import java.util.Optional;

public interface MeetingService {

     Optional<Meeting> findByUserAndSlotId(Long userId, Long slotId);
}
