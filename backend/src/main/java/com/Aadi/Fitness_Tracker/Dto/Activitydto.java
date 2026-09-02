package com.Aadi.Fitness_Tracker.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class Activitydto {

    private Long id;

    private Date date;

    private int steps;

    private double distance;

    private int caloriesburned;



}
