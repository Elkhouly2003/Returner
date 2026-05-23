package com.example.Returner.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private String email;

    private String phone;

    private String address;

    private List<CourseResponse> courses;

}
