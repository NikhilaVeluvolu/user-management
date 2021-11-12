package com.infosys.interview.usermanagement.controller;

import com.infosys.interview.usermanagement.entity.User;
import com.infosys.interview.usermanagement.request.UserRequest;
import com.infosys.interview.usermanagement.response.UserResponse;
import com.infosys.interview.usermanagement.response.UsersResponse;
import com.infosys.interview.usermanagement.service.UserService;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeTest
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetUsers() {

        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUsers(Collections.singletonList(new UserResponse("name", 10, "job")));

        when(userService.getUsersPaginated(any())).thenReturn(usersResponse);

        ResponseEntity<UsersResponse> usersResponseResponseEntity = userController.getUsers(new UserRequest());

        Assert.assertEquals(usersResponseResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUserById() {

        when(userService.getUserById(any())).thenReturn(Optional.of(new User()));

        ResponseEntity<User> userResponseEntity = userController.getUserById(10);

        Assert.assertEquals(userResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCreateUser() {

        when(userService.createUser(any())).thenReturn(new User());

        ResponseEntity<User> userResponseEntity = userController.createUser(new User());

        Assert.assertEquals(userResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testUpdateUser() {

        when(userService.updateUser(any())).thenReturn(new User());

        ResponseEntity<User> userResponseEntity = userController.updateUser(10, new User());

        Assert.assertEquals(userResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteUser() {

        doNothing().when(userService).deleteUser(any());

        ResponseEntity<HttpStatus> userResponseEntity = userController.deleteUser(10);

        Assert.assertEquals(userResponseEntity.getStatusCode(), HttpStatus.OK);

    }
}
