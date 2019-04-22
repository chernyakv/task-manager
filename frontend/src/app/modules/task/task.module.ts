import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TasksComponent } from './components/table/tasks.component';
import { TaskDetailComponent } from './components/task-detail/task-detail.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ModalModule } from 'ngx-bootstrap';
import { NewProjectModalComponent } from '../project/components/new-project-modal/new-project-modal.component';




@NgModule({
  declarations: [      
    TasksComponent,
    TaskDetailComponent,
    NewProjectModalComponent      
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
  entryComponents:[NewProjectModalComponent]
  
})
export class TaskModule { }
