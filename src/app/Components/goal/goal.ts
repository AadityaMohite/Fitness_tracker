import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { User } from '../../Service/user';
import { SharedModule } from '../../shared/shared-module';

@Component({
  selector: 'app-goal',
  imports: [SharedModule],
  templateUrl: './goal.html',
  styleUrl: './goal.css',
})
export class Goal {

    
    gridStyle = {
    width: '100%',
    textAlign: 'center'

  };

   goalForm!:FormGroup;
  goals: any;



  constructor(private fb: FormBuilder, private message: NzMessageService, private userservice: User) {

  }

  ngOnInit(){
    this.goalForm =this.fb.group({
      description:[null,[Validators.required]],
      starting_date:[null,[Validators.required]],
      ending_date:[null,[Validators.required]],
      
    })
    this.getgoals();
  }

   Submitform(){
      this.userservice.postGoal(this.goalForm.value).subscribe(res=>{
       this.message.success("Goal posted Successfully",{nzDuration:5000});
       this.goalForm.reset();
       this.getgoals();

      },error=>{
        this.message.error("error while posting activity",{nzDuration:5000});
      })
    }

 getgoals(){

  this.userservice.getGoals().subscribe(res=>{
      this.goals = res;
      console.log(this.goals);
  })


 };

 updateStatus(id:number){
    this.userservice.updateStatusGoals(id).subscribe(res=>{
     this.message.success("Goal updated Sucessfully",{nzDuration:5000});
     this.getgoals();
    },error=>{
      this.message.error("Error While updating goal",{nzDuration:5000})
    })
 }
    
      
      



}
