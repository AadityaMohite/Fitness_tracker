package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.GoalDto;

import java.util.List;

public interface Goal_Service {

    public GoalDto postGoal(GoalDto goalDto);
    public List<GoalDto> getGoals();
    public GoalDto updatestatus(Long id);
}
