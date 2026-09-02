package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.Activitydto;
import com.Aadi.Fitness_Tracker.Dto.GraphDto;
import com.Aadi.Fitness_Tracker.Dto.StatsDto;
import com.Aadi.Fitness_Tracker.Dto.WorkoutDto;
import com.Aadi.Fitness_Tracker.Entity.Activity;
import com.Aadi.Fitness_Tracker.Entity.User;
import com.Aadi.Fitness_Tracker.Entity.Workout;
import com.Aadi.Fitness_Tracker.Repository.Activity_Repository;
import com.Aadi.Fitness_Tracker.Repository.Goal_Repository;
import com.Aadi.Fitness_Tracker.Repository.Workout_Repository;
import com.Aadi.Fitness_Tracker.Security.AuthenticatedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Stats_Serviceimpl implements Stats_service {

    @Autowired
    private Goal_Repository goalRepository;

    @Autowired
    private Activity_Repository activityRepository;

    @Autowired
    private Workout_Repository workoutRepository;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;


    public StatsDto getStats() {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        Long achievedGoals = goalRepository.countAchievedGoals(currentUser);

        Long notAchievedGoals = goalRepository.countNotAchievedGoals(currentUser);

        Integer totalSteps = activityRepository.counttotalSteps(currentUser);

        Double totalDistance = activityRepository.counttotalDistance(currentUser);

        Integer totalactivityCaloriesburned = activityRepository.counttoalCaloriesburned(currentUser);

        Integer totalworkoutCaloriesburned = workoutRepository.counttotalworkoutCaloriesburned(currentUser);

        Integer totalworkoutduration = workoutRepository.countotalworkoutduration(currentUser);


        int totalCaloriesburned = (totalactivityCaloriesburned != null ? totalactivityCaloriesburned : 0) +
                (totalworkoutCaloriesburned != null ? totalworkoutCaloriesburned : 0);


        StatsDto statsDto = new StatsDto();


        statsDto.setAchievedGoals(achievedGoals != null ? achievedGoals : 0);
        statsDto.setNotAchievedGoals(notAchievedGoals != null ? notAchievedGoals : 0);
        statsDto.setSteps(totalSteps != null ? totalSteps : 0);
        statsDto.setDistance(totalDistance != null ? totalDistance : 0);
        statsDto.setTotalCaloriesburned(totalCaloriesburned);
        statsDto.setDuration(totalworkoutduration != null ? totalworkoutduration : 0);


        return statsDto;


    }

    public GraphDto getGraphStats() {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        List<Workout> workouts = workoutRepository.findlast7Workouts(currentUser);

        List<Activity> activities = activityRepository.findlast7Activities(currentUser);

        List<WorkoutDto> workoutDtos = workouts.stream()
                .map(workout -> {
                    WorkoutDto dto = new WorkoutDto();

                    dto.setId(workout.getId());
                    dto.setType(workout.getType());
                    dto.setDate(workout.getDate());
                    dto.setDuration(workout.getDuration());
                    dto.setCaloriesburned(workout.getCaloriesburned());

                    return dto;
                })
                .toList();


        List<Activitydto> activityDtos = activities.stream()
                .map(activity -> {
                    Activitydto dto = new Activitydto();

                    dto.setId(activity.getId());
                    dto.setSteps(activity.getSteps());
                    dto.setDate(activity.getDate());
                    dto.setDistance(activity.getDistance());
                    dto.setCaloriesburned(activity.getCaloriesburned());

                    return dto;
                })
                .toList();


        GraphDto graphDto = new GraphDto();

        graphDto.setWorkouts(workoutDtos);
        graphDto.setActivities(activityDtos);


        return graphDto;
    }


}
