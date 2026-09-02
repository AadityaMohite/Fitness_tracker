import { Component, ElementRef, ViewChild } from '@angular/core';
import { User } from '../../Service/user';
import { SharedModule } from '../../shared/shared-module';
import Chart, { CategoryScale } from 'chart.js/auto';
import { DatePipe } from '@angular/common';

Chart.register(CategoryScale);

@Component({
  selector: 'app-dashboard',
  imports: [SharedModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
  providers :[DatePipe]
})
export class Dashboard {

  statsData : any;

  workouts : any;
  activities : any ;

  @ViewChild('workoutLineChart') private workoutLineChartRef:ElementRef;
  @ViewChild('activityLineChart') private activityLineChartRef:ElementRef;

  ngOnInit():void{
     this.getStats();
     this.getGraphStats();
  };

  constructor(private userservice : User, private datepipe : DatePipe){
  }

  getStats(){
    this.userservice.getStats().subscribe(res=>{
      console.log(res);
        this.statsData = res;
        
    })
  };


    getGraphStats(){
       this.userservice.getGraphs().subscribe(res=>{
          this.workouts = res.workouts;
          this.activities = res.activities;
          console.log(this.workouts);
          console.log(this.activities);
          if(this.workoutLineChartRef || this.activityLineChartRef){
            this.createLineChart();
          }
       })
    };

    ngAfterViewInit(){
      if(this.workouts && this.activities){
        this.createLineChart();
      }
    };

    createLineChart(){
      const workoutctx = this.workoutLineChartRef.nativeElement.getContext('2d');
      const activityctx = this.activityLineChartRef.nativeElement.getContext('2d');
      new Chart(workoutctx, {
    type: 'line',
    data: {
      labels: this.workouts.map((data:{date:any})=>this.datepipe.transform(data.date,'MM/dd')),
      datasets: [
         {
        label: 'Calories burned',
        data: this.workouts.map((item: { caloriesburned: any }) => item.caloriesburned),
         fill : false,
         borderWidth:2,
         backgroundColor : 'rgba(80,200,120,0.6)',
         borderColor :  'rgba(0,100,0,1)',
      },
        {
        label: 'Duration',
        data: this.workouts.map((item : {duration : any})=>item.duration),
       fill : false,
       backgroundColor : 'rgba(120,180,200,0.6)',
         borderColor :  'rgba(0,100,150,1)',
      }
    ]
    },
    options: {
      responsive: true,
  maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
  new Chart(activityctx, {
    type: 'line',
    data: {
      labels: this.activities.map((data:{date:any})=>this.datepipe.transform(data.date,'MM/dd')),
      datasets: [
         {
        label: 'Calories burned',
        data: this.activities.map((item: { caloriesburned: any }) => item.caloriesburned),
         fill : false,
         borderWidth:2,
         backgroundColor : 'rgba(255,100,100,0.6)',
         borderColor :  'rgba(255,0,0,1)',
      },
        {
        label: 'Steps',
        data: this.activities.map((item : {steps : any})=>item.steps),
       fill : false,
       backgroundColor : 'rgba(255,180,120,0.6)',
         borderColor :  'rgba(255,100,0,1)',
      },
      {
        label: 'Distance',
        data: this.activities.map((item : {distance : any})=>item.distance),
       fill : false,
       backgroundColor : 'rgba(255,200,200,0.6)',
         borderColor :  'rgba(255,0,100,1)',
      }
    ]
    },
    options: {
      responsive: true,
  maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
    }

}
