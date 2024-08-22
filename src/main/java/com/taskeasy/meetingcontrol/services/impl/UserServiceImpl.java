package com.taskeasy.meetingcontrol.services.impl;

import com.taskeasy.meetingcontrol.entities.User;
import com.taskeasy.meetingcontrol.repositories.UserRepository;
import com.taskeasy.meetingcontrol.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
