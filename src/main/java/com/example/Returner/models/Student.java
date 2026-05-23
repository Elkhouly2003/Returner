package com.example.Returner.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "student_age")
    private int age;

    @Column(name = "student_gender")
    private String gender;

    @Column(name = "student_phone")
    private String phone;

    @Column(name = "student_address")
    private String address;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
           name = "student_courses" ,
           joinColumns = @JoinColumn(name = "student_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id")
   )
    private List<Course> courses;

   @OneToOne
   @JoinColumn(name = "user_id")
   private User user;
}
