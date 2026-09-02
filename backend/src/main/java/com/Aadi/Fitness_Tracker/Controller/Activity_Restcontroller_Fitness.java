package com.Aadi.Fitness_Tracker.Controller;
import com.Aadi.Fitness_Tracker.Dto.Activitydto;
import com.Aadi.Fitness_Tracker.Dto.WorkoutDto;
import com.Aadi.Fitness_Tracker.Service.Activity_Service;
import com.Aadi.Fitness_Tracker.Service.Workout_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Activity_Restcontroller_Fitness {

    @Autowired
      private Activity_Service activityservice;


    @PostMapping("/save")
      public ResponseEntity<?> postactivity(@RequestBody Activitydto activitydto){

           Activitydto createactivity =activityservice.postactivity(activitydto);


           if(createactivity!=null){
               return ResponseEntity.status(HttpStatus.CREATED).body(createactivity);
           }else{
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing is Went Wrong");
           }

      }

      @GetMapping("/getActivity")
    public ResponseEntity<?> getactivity (){
         try{
              return ResponseEntity.ok(activityservice.getActivities());
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing went wrong");
         }
      }



}
