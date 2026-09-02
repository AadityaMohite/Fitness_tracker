import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class User {

    private Basic_URL = `${environment.apiUrl}/`;

   constructor(private httpclient :HttpClient){

   }

   postActivity(activityDto:any):Observable<any>{
    return this.httpclient.post<any>(this.Basic_URL + 'api/save', activityDto);
   }

   getactivities():Observable<any>{
    return this.httpclient.get<any>(this.Basic_URL + 'api/getActivity');
   }

   postWorkout(workoutDto:any):Observable<any>{
    return this.httpclient.post<any>(this.Basic_URL + 'api/saveWorkout', workoutDto);
   }

   getWorkouts():Observable<any>{
    return this.httpclient.get<any>(this.Basic_URL + 'api/getWorkouts');
   }

   postGoal(goalDto:any):Observable<any>{
    return this.httpclient.post<any>(this.Basic_URL + 'api/postGoal', goalDto);
   }
   
   getGoals():Observable<any>{
    return this.httpclient.get<any>(this.Basic_URL + 'api/getGoals');
   }

   updateStatusGoals(id:number):Observable<any>{
    return this.httpclient.get<any>(this.Basic_URL + 'api/goal/status/'+id);
   }


    getStats(){
      return this.httpclient.get<any>(this.Basic_URL + 'api/stats');
    }

    getGraphs(){
      return this.httpclient.get<any>(this.Basic_URL+'api/graphs');
    }
}
