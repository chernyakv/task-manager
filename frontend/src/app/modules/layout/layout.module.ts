import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { TaskModule } from '../task/task.module';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserService } from 'src/app/_services/user.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';

@NgModule({
  declarations: [
    NotFoundComponent,
    HomeComponent,
    LoginComponent],
  imports: [
    CommonModule,
    TaskModule,
    ReactiveFormsModule
  ],  
  providers: [UserService, AuthenticationService]
})
export class LayoutModule { }
