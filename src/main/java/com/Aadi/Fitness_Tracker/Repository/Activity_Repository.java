package com.Aadi.Fitness_Tracker.Repository;

import com.Aadi.Fitness_Tracker.Entity.Activity;
import com.Aadi.Fitness_Tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Activity_Repository extends JpaRepository<Activity, Long> {

    @Query("SELECT SUM(a.steps) FROM Activity a WHERE a.user = :user")
    Integer counttotalSteps(@Param("user") User user);

    @Query("SELECT SUM(a.distance) FROM Activity a WHERE a.user = :user")
    Double counttotalDistance(@Param("user") User user);

    @Query("SELECT SUM(a.caloriesburned) FROM Activity a WHERE a.user = :user")
    Integer counttoalCaloriesburned(@Param("user") User user);

    @Query("SELECT a FROM Activity a WHERE a.user = :user ORDER BY a.date DESC")
    List<Activity> findlast7Activities(@Param("user") User user);

    List<Activity> findByUser(User user);

}
