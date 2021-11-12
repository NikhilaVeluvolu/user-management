package com.infosys.interview.usermanagement.request;

import lombok.Data;
import org.springframework.data.domain.Sort;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@Data
public class PaginationRequest {

    private int pageNum = 0;
    private int pageSize = 10;
    private Sort.Direction sortOrder = Sort.Direction.DESC;
    private String sortField = "id";
}
