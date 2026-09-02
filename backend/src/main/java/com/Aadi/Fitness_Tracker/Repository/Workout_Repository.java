package com.Aadi.Fitness_Tracker.Repository;

import com.Aadi.Fitness_Tracker.Entity.User;
import com.Aadi.Fitness_Tracker.Entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Workout_Repository extends JpaRepository<Workout, Long> {

    @Query("SELECT SUM(w.caloriesburned) FROM Workout w WHERE w.user = :user")
    Integer counttotalworkoutCaloriesburned(@Param("user") User user);

    @Query("SELECT SUM(w.duration) FROM Workout w WHERE w.user = :user")
    Integer countotalworkoutduration(@Param("user") User user);

    @Query("SELECT w FROM Workout w WHERE w.user = :user ORDER BY w.date DESC")
    List<Workout> findlast7Workouts(@Param("user") User user);

    List<Workout> findByUser(User user);
}
