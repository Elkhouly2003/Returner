package com.example.Returner.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {

    private Long id;

    private String name;

    private String code;

    private int nHours;

}