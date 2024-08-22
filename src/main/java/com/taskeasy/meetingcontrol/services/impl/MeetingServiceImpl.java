package com.taskeasy.meetingcontrol.services.impl;

import com.taskeasy.meetingcontrol.entities.Meeting;
import com.taskeasy.meetingcontrol.repositories.MeetingRepository;
import com.taskeasy.meetingcontrol.services.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    @Override
    public Optional<Meeting> findByUserAndSlotId(Long userId, Long slotId) {
        return meetingRepository.findByUserIdAndSlotId(userId, slotId);
    }

}
