package com.infosys.interview.usermanagement.repository;

import com.infosys.interview.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
