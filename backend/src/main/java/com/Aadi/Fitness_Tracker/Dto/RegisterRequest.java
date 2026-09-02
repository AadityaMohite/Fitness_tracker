package com.Aadi.Fitness_Tracker.Dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;

    private String email;

    private String password;
}
