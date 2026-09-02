package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.Activitydto;

import java.util.List;

public interface Activity_Service {

    public Activitydto postactivity (Activitydto activitydto);
    public List<Activitydto> getActivities();
}
