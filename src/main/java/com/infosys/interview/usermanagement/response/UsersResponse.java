package com.infosys.interview.usermanagement.response;

import lombok.Data;

import java.util.List;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@Data
public class UsersResponse {

    private int numOfRecords;
    private int numOfPages;
    private List<UserResponse> users;
}
