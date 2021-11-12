package com.infosys.interview.usermanagement.request;

import lombok.Data;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@Data
public class UserRequest {

    private int id;
    private String job;
    private String name;
    private PaginationRequest pagination;
}
