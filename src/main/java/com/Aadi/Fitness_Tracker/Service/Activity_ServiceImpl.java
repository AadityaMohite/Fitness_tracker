package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.Activitydto;
import com.Aadi.Fitness_Tracker.Entity.Activity;
import com.Aadi.Fitness_Tracker.Entity.User;
import com.Aadi.Fitness_Tracker.Repository.Activity_Repository;
import com.Aadi.Fitness_Tracker.Security.AuthenticatedUserProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Activity_ServiceImpl implements Activity_Service {

    @Autowired
    private Activity_Repository activityRepository;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;


    public Activitydto postactivity(Activitydto dto) {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        Activity activity = new Activity();

        BeanUtils.copyProperties(dto, activity);
        activity.setUser(currentUser);

        Activity savedactivity = activityRepository.save(activity);

        Activitydto responsedto = new Activitydto();

        BeanUtils.copyProperties(savedactivity, responsedto);

        return responsedto;
    }

    public List<Activitydto> getActivities() {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        List<Activity> activities = activityRepository.findByUser(currentUser);

        List<Activitydto> activitydto = new ArrayList<>();

        for (Activity activity : activities) {
            Activitydto activitydto1 = new Activitydto();

            BeanUtils.copyProperties(activity, activitydto1);

            activitydto.add(activitydto1);
        }

        return activitydto;

    }
}
