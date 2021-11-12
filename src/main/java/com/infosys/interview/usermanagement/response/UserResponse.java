package com.infosys.interview.usermanagement.response;

import lombok.Data;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@Data
public class UserResponse {

    private String name;
    private int age;
    private String job;

    public UserResponse(final String name, final int age, final String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

}
