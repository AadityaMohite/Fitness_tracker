package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.WorkoutDto;
import com.Aadi.Fitness_Tracker.Entity.User;
import com.Aadi.Fitness_Tracker.Entity.Workout;
import com.Aadi.Fitness_Tracker.Repository.Workout_Repository;
import com.Aadi.Fitness_Tracker.Security.AuthenticatedUserProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Workout_ServiceImpl implements Workout_Service {

    @Autowired
    private Workout_Repository workoutRepository;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;


    public WorkoutDto postWorkout(WorkoutDto workoutDto) {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        Workout workout = new Workout();

        BeanUtils.copyProperties(workoutDto, workout);
        workout.setUser(currentUser);

        Workout saveworkout = workoutRepository.save(workout);

        WorkoutDto responsedto = new WorkoutDto();

        BeanUtils.copyProperties(saveworkout, responsedto);

        return responsedto;
    }

    public List<WorkoutDto> getWorkouts() {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        List<Workout> workoutoList = workoutRepository.findByUser(currentUser);

        List<WorkoutDto> workoutDtos = new ArrayList<>();

        for (Workout workout : workoutoList) {
            WorkoutDto workoutDto1 = new WorkoutDto();

            BeanUtils.copyProperties(workout, workoutDto1);

            workoutDtos.add(workoutDto1);
        }

        return workoutDtos;
    }
}
