package com.Aadi.Fitness_Tracker.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class WorkoutDto {

    private Long id;

    private String type;

    private Date date;

    private int duration;

    private int caloriesburned;
}
