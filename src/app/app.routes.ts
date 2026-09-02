import { Routes } from '@angular/router';
import { Activity } from './Components/activity/activity';
import { Workout } from './Components/workout/workout';
import { Goal } from './Components/goal/goal';
import { Dashboard } from './Components/dashboard/dashboard';
import { Login } from './Components/login/login';
import { Register } from './Components/register/register';
import { MainLayout } from './Components/main-layout/main-layout';
import { authGuard } from './Service/auth-guard';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'register', component: Register },
    {
        path: '',
        component: MainLayout,
        canActivate: [authGuard],
        children: [
            { path: 'dashboard', component: Dashboard },
            { path: 'workout', component: Workout },
            { path: 'activity', component: Activity },
            { path: 'goal', component: Goal },
            { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
        ],
    },
    { path: '**', redirectTo: '' },
];
