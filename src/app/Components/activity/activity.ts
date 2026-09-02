import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared-module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { User } from '../../Service/user';

@Component({
  selector: 'app-activity',
  imports: [SharedModule],
  templateUrl: './activity.html',
  styleUrl: './activity.css',
})
export class Activity {
  gridStyle={
    width:'100%',
    textAlign:'center'

  };

  activityForm!:FormGroup;
  activities: any;

  constructor(private fb :FormBuilder,private message : NzMessageService,private userservice : User){

  }
  ngOnInit(){
    this.activityForm =this.fb.group({
      caloriesburned:[null,[Validators.required]],
      steps:[null,[Validators.required]],
      distance:[null,[Validators.required]],
      date : [null,[Validators.required]]
    })
    this.getactivities();
  }

    Submitform(){
      this.userservice.postActivity(this.activityForm.value).subscribe(res=>{
       this.message.success("Activity posted Successfully",{nzDuration:5000});
       this.activityForm.reset();
       this.getactivities();
      },error=>{
        this.message.error("error while posting activity",{nzDuration:5000});
      })
    }

    getactivities(){
      this.userservice.getactivities().subscribe(res=>{
         this.activities = res;
         console.log(this.activities);
      })
    }
}
