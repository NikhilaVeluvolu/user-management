package com.infosys.interview.usermanagement.controller;

import com.infosys.interview.usermanagement.entity.User;
import com.infosys.interview.usermanagement.request.UserRequest;
import com.infosys.interview.usermanagement.response.UsersResponse;
import com.infosys.interview.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<UsersResponse> getUsers(@RequestBody UserRequest userRequest) {
        try {
            UsersResponse usersResponse = userService.getUsersPaginated(userRequest);

            if (usersResponse == null || usersResponse.getUsers().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> UserData = userService.getUserById(id);

        return UserData.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userService.createUser(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> UserData = userService.getUserById(id);

        if (UserData.isPresent()) {
            User _user = UserData.get();
            _user.setAge(user.getAge());
            _user.setDescription(user.getDescription());
            _user.setHeight(user.getHeight());
            _user.setName(user.getName());
            _user.setJob(user.getJob());
            return new ResponseEntity<>(userService.updateUser(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
