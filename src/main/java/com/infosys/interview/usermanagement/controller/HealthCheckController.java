package com.infosys.interview.usermanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @Author: Nikhila Veluvolu
 CreatedOn: 12 Nov 2021
 */
@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @GetMapping("/heartbeat")
    public ResponseEntity<String> checkHeartbeat() {

        return new ResponseEntity<>("Service is up and running!!", HttpStatus.OK);
    }
}
