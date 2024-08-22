package com.taskeasy.meetingcontrol.repositories;

import com.taskeasy.meetingcontrol.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
