package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.GoalDto;
import com.Aadi.Fitness_Tracker.Entity.Goal;
import com.Aadi.Fitness_Tracker.Entity.User;
import com.Aadi.Fitness_Tracker.Repository.Goal_Repository;
import com.Aadi.Fitness_Tracker.Security.AuthenticatedUserProvider;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Goal_ServiceImpl implements Goal_Service {

    @Autowired
    private Goal_Repository goalRepository;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;

    public GoalDto postGoal(GoalDto goalDto) {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        Goal goal = new Goal();
        BeanUtils.copyProperties(goalDto, goal);
        goal.setUser(currentUser);

        Goal savegoal = goalRepository.save(goal);
        GoalDto goalDto1 = new GoalDto();
        BeanUtils.copyProperties(savegoal, goalDto1);
        return goalDto1;
    }

    public List<GoalDto> getGoals() {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        List<Goal> goalList = goalRepository.findByUser(currentUser);

        List<GoalDto> GoalsDtos = new ArrayList<>();

        for (Goal goal : goalList) {
            GoalDto goalDto1 = new GoalDto();

            BeanUtils.copyProperties(goal, goalDto1);
            GoalsDtos.add(goalDto1);
        }

        return GoalsDtos;
    }


    public GoalDto updatestatus(Long id) {

        User currentUser = authenticatedUserProvider.getCurrentUser();

        Optional<Goal> optionalgoal = goalRepository.findById(id);

        if (optionalgoal.isPresent()) {
            Goal existingGoal = optionalgoal.get();

            if (existingGoal.getUser() == null || !existingGoal.getUser().getId().equals(currentUser.getId())) {
                throw new AccessDeniedException("You are not allowed to modify this goal");
            }

            GoalDto goalDto3 = new GoalDto();
            existingGoal.setAchieved(true);
            Goal updategoal = goalRepository.save(existingGoal);

            BeanUtils.copyProperties(updategoal, goalDto3);
            return goalDto3;
        } else {
            throw new EntityNotFoundException("Goal Not found");
        }

    }

}
