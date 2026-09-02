package com.Aadi.Fitness_Tracker.Repository;

import com.Aadi.Fitness_Tracker.Entity.Goal;
import com.Aadi.Fitness_Tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Goal_Repository extends JpaRepository<Goal, Long> {

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = true AND g.user = :user")
    Long countAchievedGoals(@Param("user") User user);

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = false AND g.user = :user")
    Long countNotAchievedGoals(@Param("user") User user);

    List<Goal> findByUser(User user);
}
