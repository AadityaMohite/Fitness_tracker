package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.GraphDto;
import com.Aadi.Fitness_Tracker.Dto.StatsDto;

public interface Stats_service {
    public StatsDto getStats();
    public GraphDto getGraphStats();
}
