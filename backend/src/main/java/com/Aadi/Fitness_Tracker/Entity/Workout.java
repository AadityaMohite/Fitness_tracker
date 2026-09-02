package com.Aadi.Fitness_Tracker.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    private String type;

    private Date date;

    private int duration;

    private int caloriesburned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
