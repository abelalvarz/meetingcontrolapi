package com.taskeasy.meetingcontrol.services.impl;

import com.taskeasy.meetingcontrol.entities.User;
import com.taskeasy.meetingcontrol.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().id(1L).name("kyle").build());
        userList.add(User.builder().id(2L).name("Luis").build());

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> userExpected = userService.findAllUser();
        assertEquals(2, userExpected.size());
        assertEquals("kyle", userExpected.get(0).getName());
        assertEquals("Luis", userExpected.get(1).getName());
    }
}