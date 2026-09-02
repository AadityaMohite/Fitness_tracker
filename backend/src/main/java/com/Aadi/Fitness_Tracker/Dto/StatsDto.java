package com.Aadi.Fitness_Tracker.Dto;

import lombok.Data;

@Data
public class StatsDto {


     private long achievedGoals;

     private long notAchievedGoals;

     private int steps;

     private double distance;

     private int totalCaloriesburned;

     private int duration;

}
