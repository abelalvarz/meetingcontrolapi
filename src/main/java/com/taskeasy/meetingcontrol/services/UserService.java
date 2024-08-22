package com.taskeasy.meetingcontrol.services;

import com.taskeasy.meetingcontrol.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
}
