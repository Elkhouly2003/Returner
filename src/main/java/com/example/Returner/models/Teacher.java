package com.example.Returner.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    @Column(name = "teacher_name")
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Course>courses ;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;


}
