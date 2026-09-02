package com.Aadi.Fitness_Tracker.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class GoalDto {
    private Long id;

    private String description;

    private Date starting_date;

    private Date ending_date;

    private boolean achieved;
}
