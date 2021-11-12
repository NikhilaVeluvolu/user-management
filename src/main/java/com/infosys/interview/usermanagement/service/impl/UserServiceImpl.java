package com.infosys.interview.usermanagement.service.impl;

import com.infosys.interview.usermanagement.entity.User;
import com.infosys.interview.usermanagement.repository.UserRepository;
import com.infosys.interview.usermanagement.request.UserRequest;
import com.infosys.interview.usermanagement.response.UserResponse;
import com.infosys.interview.usermanagement.response.UsersResponse;
import com.infosys.interview.usermanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(value = "usersCache")
    public List<User> getAllUsers() {
        log.info("getting all users from the database");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "usersPaginatedCache")
    public UsersResponse getUsersPaginated(UserRequest userRequest) {
        log.info("getting users in paginated format for the given userRequest: {}", userRequest);
        Pageable pageable = createPageRequest(userRequest);
        Page<User> userPage = userRepository.findAll(pageable);

        List<User> users = userPage.getContent();

        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUsers(users.stream().map(user-> new UserResponse(user.getName(), user.getAge(), user.getJob()))
                .collect(Collectors.toList()));
        usersResponse.setNumOfPages(userPage.getTotalPages());
        usersResponse.setNumOfRecords(userPage.getNumberOfElements());
        return usersResponse;
    }

    @Override
    @Cacheable(value = "usersCache", key = "#id")
    public Optional<User> getUserById(Long id) {
        log.info("get user by id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    @CacheEvict(value = "usersCache", allEntries = true)
    public User createUser(User user) {
        log.info("creating a user for userRequest: {}", user);
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "usersCache", allEntries = true)
    public User updateUser(User user) {
        log.info("updating a user for userRequest: {}", user);
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "usersCache", key = "#id")
    public void deleteUser(Long id) {
        log.info("deleting a user by id: {}", id);
        Optional<User> userOptional = userRepository.findById(id);

        userOptional.ifPresent(user -> userRepository.delete(user));
    }

    private Pageable createPageRequest(UserRequest userRequest) {
        log.info("creating page request for the userRequest: {}", userRequest);
        return PageRequest.of(userRequest.getPagination().getPageNum(), userRequest.getPagination().getPageSize(),
                userRequest.getPagination().getSortOrder(),
                userRequest.getPagination().getSortField());
    }
}
