package com.Aadi.Fitness_Tracker.Dto;

import lombok.Data;

import java.util.List;


@Data
public class GraphDto {

       private List<WorkoutDto> workouts;

    private List<Activitydto>  activities;
}
