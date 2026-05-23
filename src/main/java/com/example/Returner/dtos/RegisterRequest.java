package com.example.Returner.dtos;

import com.example.Returner.securityConfiguration.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}