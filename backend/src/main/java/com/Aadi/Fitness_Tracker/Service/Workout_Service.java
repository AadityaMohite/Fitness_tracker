package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.WorkoutDto;

import java.util.List;

public interface Workout_Service {
    public WorkoutDto postWorkout(WorkoutDto workoutDto);
    public List<WorkoutDto> getWorkouts();
}
