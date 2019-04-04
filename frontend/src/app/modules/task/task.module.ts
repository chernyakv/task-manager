import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TasksComponent } from './components/table/tasks.component';
import { TaskDetailComponent } from './components/task-detail/task-detail.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ModalModule } from 'ngx-bootstrap';
import { NewUserModalComponent } from './components/modals/new-user-modal/new-user-modal.component';
import { NewProjectModalComponent } from './components/modals/new-project-modal/new-project-modal.component';
import { NewTaskModalComponent } from './components/modals/new-task-modal/new-task-modal.component';

@NgModule({
  declarations: [      
    TasksComponent,
    TaskDetailComponent,
    NewUserModalComponent,
    NewProjectModalComponent,
    NewTaskModalComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule    
  ],
  exports: [TasksComponent, TaskDetailComponent],
  entryComponents:[NewUserModalComponent, NewProjectModalComponent]
})
export class TaskModule { }
