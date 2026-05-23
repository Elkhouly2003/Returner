package com.example.Returner.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String phone;
    private String address;

}
