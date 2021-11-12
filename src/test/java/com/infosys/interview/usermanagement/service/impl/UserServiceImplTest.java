package com.infosys.interview.usermanagement.service.impl;

import com.infosys.interview.usermanagement.entity.User;
import com.infosys.interview.usermanagement.repository.UserRepository;
import com.infosys.interview.usermanagement.request.UserRequest;
import com.infosys.interview.usermanagement.response.UsersResponse;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeTest
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetAllUsers() {

        when(userRepository.findAll()).thenReturn(Collections.singletonList(new User()));

        List<User> userList = userService.getAllUsers();

        Assert.assertTrue(userList.size()> 0);
    }

    @Test
    public void testGetUsersPaginated() {

        when(userRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(Collections.singletonList(new User())));

        UsersResponse usersResponse = userService.getUsersPaginated(new UserRequest());

        Assert.assertEquals(1, usersResponse.getUsers().size());
    }

    @Test
    public void testGetUserById() {

        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        Optional<User> userOptional = userService.getUserById(10L);

        Assert.assertNotNull(userOptional.get());
    }

    @Test
    public void testCreateUser() {

        when(userRepository.save(any())).thenReturn(new User());

        User user = userService.createUser(new User());

        Assert.assertNotNull(user);
    }

    @Test
    public void testUpdateUser() {

        when(userRepository.save(any())).thenReturn(new User());

        User user = userService.updateUser(new User());

        Assert.assertNotNull(user);
    }

    @Test
    public void testDeleteUser() {

        doNothing().when(userRepository).delete(any());

        userService.deleteUser(10L);
    }
}
