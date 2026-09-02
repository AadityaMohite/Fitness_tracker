package com.Aadi.Fitness_Tracker.Controller;


import com.Aadi.Fitness_Tracker.Dto.WorkoutDto;
import com.Aadi.Fitness_Tracker.Service.Workout_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class Workout_Restcontroller_fitness {

    @Autowired
    private Workout_Service workoutService;



    @PostMapping("/saveWorkout")
    public ResponseEntity<?> postWorkout(@RequestBody WorkoutDto workoutDto){
        WorkoutDto createworkout = workoutService.postWorkout(workoutDto);

        if(createworkout!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createworkout);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing is Went Wrong");
        }
    }

    @GetMapping("/getWorkouts")
    public ResponseEntity<?> getWorkout() {
        try {
            return ResponseEntity.ok(workoutService.getWorkouts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing went wrong");
        }
    }

}
