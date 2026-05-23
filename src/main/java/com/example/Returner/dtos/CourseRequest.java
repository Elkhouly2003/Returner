package com.example.Returner.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {
    @NotNull
    private String name;
    private String code;
    private Integer nHours ;

}
