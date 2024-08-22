package com.taskeasy.meetingcontrol.repositories;

import com.taskeasy.meetingcontrol.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query(value = "select u from Meeting u where u.user.id = :userId and u.timeSlot.id= :slotId")
    Optional<Meeting> findByUserIdAndSlotId(Long userId, Long slotId);

}
