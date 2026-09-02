package com.Aadi.Fitness_Tracker.Controller;

import com.Aadi.Fitness_Tracker.Dto.GraphDto;
import com.Aadi.Fitness_Tracker.Service.Stats_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatsController {

    @Autowired
    private Stats_service statsService;


    @GetMapping("/stats")
      public ResponseEntity<?> getStats(){

          return ResponseEntity.ok(statsService.getStats());
      }

      @GetMapping("/graphs")
      public ResponseEntity<?>getGraphStats(){

               GraphDto graphDto = statsService.getGraphStats();

               if(graphDto != null){
                   return ResponseEntity.ok(graphDto);
               }else{
                   return ResponseEntity.status(484).body(null);
               }


      }
}
