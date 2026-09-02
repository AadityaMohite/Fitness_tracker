import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared-module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { User } from '../../Service/user';

@Component({
  selector: 'app-workout',
  imports: [SharedModule],
  templateUrl: './workout.html',
  styleUrl: './workout.css',
})
export class Workout {


  gridStyle = {
    width: '100%',
    textAlign: 'center'

  };

  workoutForm!: FormGroup;



  listofType: string[] = [
    "Cardio",
    "Strength",
    "Flexibility",
    "Endurance",
    "HIIT",
    "Yoga",
    "Pilates",
    "CrossFit",
    "Weight Training",
    "Bodyweight",
    "Stretching",
    "Mobility",
    "Balance",
    "Circuit Training",
    "Aerobic"
  ];

  constructor(private fb: FormBuilder, private message: NzMessageService, private userservice: User) {

  }

  workouts : any;

  ngOnInit(): void {
    this.workoutForm = this.fb.group({
      caloriesburned: [null,
        [Validators.required,
        Validators.min(1),
        Validators.max(10000)
        ]
      ],
      type:[null,[Validators.required]],
      duration: [null,[
          Validators.required,
          Validators.min(1),
          Validators.max(600)
        ]],
      date:[null,[Validators.required]]
    });

    this.getworkouts();

  }
  
  submitForm(){
    this.userservice.postWorkout(this.workoutForm.value).subscribe(res=>{
          this.message.success("Workout posted Successfully",{nzDuration:5000});
             this.getworkouts();
          this.workoutForm.reset();
       
          console.log(res);
    }, error=>{
        this.message.error("error while posting activity",{nzDuration:5000});
      })
  };

  


  resetForm():void{
    this.workoutForm.reset();
  }

  getworkouts(){
      this.userservice.getWorkouts().subscribe(res=>{
          this.workouts = res;
          console.log(this.workouts);
      });
  }

}
