import { Routes } from "@angular/router";

export const appRoutes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
    {
        path: 'home',
        title: 'School Dashboard',
        loadComponent: () => import('./components/home/home.component').then(m => m.default)
    },
    {
        path: 'students',
        title: 'Students',
        loadComponent: () => import('./components/students/students.component').then(m => m.StudentsComponent)
    },
    {
        path: 'teachers',
        title: 'Teachers',
        loadComponent: () => import('./components/teachers/teachers.component').then(m => m.TeachersComponent)
    },
    {
        path: 'courses',
        title: 'Courses',
        loadComponent: () => import('./components/courses/courses.component').then(m => m.CoursesComponent)
    },
    {
        path: 'courses/teacher/:id',
        title: 'Courses',
        loadComponent: () => import('./components/courses/courses.component').then(m => m.CoursesComponent)
    },
    {
        path: 'subscriptions',
        title: 'Subscriptions',
        loadComponent: () => import('./components/subscriptions/subscriptions.component').then(m => m.subscriptionsComponent)
    },
    {
        path: 'code',
        title: 'Code',
        loadComponent: () => import('./components/code/code.component').then(m => m.CodeComponent)
    },
]