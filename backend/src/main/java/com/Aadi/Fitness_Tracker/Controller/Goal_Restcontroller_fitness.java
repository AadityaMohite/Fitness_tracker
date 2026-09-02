package com.Aadi.Fitness_Tracker.Controller;

import com.Aadi.Fitness_Tracker.Dto.GoalDto;
import com.Aadi.Fitness_Tracker.Service.Goal_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Goal_Restcontroller_fitness {

    @Autowired
    private Goal_Service goalService;

    @PostMapping("/postGoal")
    public ResponseEntity<?> postGoal (@RequestBody GoalDto goalDto){
        GoalDto postgoal = goalService.postGoal(goalDto);

        if(postgoal!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body(postgoal);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing is Went Wrong");
        }
    }

    @GetMapping("/getGoals")
    public ResponseEntity<?> getGoal() {
        try {
            return ResponseEntity.ok(goalService.getGoals());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing went wrong");
        }
    }

    @GetMapping("/goal/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id){
        try{
            return ResponseEntity.ok(goalService.updatestatus(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




}
