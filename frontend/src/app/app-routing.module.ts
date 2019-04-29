import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './modules/layout/home/home.component';
import { LoginComponent } from './modules/layout/login/login.component';
import { AuthGuard } from './_helpers/AuthGuard';
import { AdminComponent } from './modules/layout/admin/admin.component';
import { ProjectDetailsComponent } from './modules/project/components/project-details/project-details.component';
import { TaskDetailComponent } from './modules/task/components/task-detail/task-detail.component';





const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: {roles:['ADMIN']}
  },
  {
    path: 'project-details/:id',
    component: ProjectDetailsComponent
  },
  {
    path: 'task-details/:id',
    component: TaskDetailComponent
  },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
