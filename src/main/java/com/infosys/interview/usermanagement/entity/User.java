package com.infosys.interview.usermanagement.entity;

import lombok.Data;

import javax.persistence.*;

/**
    @Author: Nikhila Veluvolu
    CreatedOn: 12 Nov 2021
 */
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "job")
    private String job;

    @Column(name = "height")
    private Double height;

    @Column(name = "description")
    private String description;

}
