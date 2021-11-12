package com.infosys.interview.usermanagement.service;

import com.infosys.interview.usermanagement.entity.User;
import com.infosys.interview.usermanagement.request.UserRequest;
import com.infosys.interview.usermanagement.response.UsersResponse;

import java.util.List;
import java.util.Optional;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
public interface UserService {

    List<User> getAllUsers();

    UsersResponse getUsersPaginated(UserRequest userRequest);

    Optional<User> getUserById(final Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(final Long id);
}
