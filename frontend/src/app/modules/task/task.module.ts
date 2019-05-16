import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TasksComponent } from './components/table/tasks.component';
import { TaskDetailComponent } from './components/task-detail/task-detail.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ModalModule, BsDatepickerModule, PaginationModule } from 'ngx-bootstrap';
import { NewProjectModalComponent } from '../project/components/new-project-modal/new-project-modal.component';
import { NewTaskModalComponent } from './components/new-task-modal/new-task-modal.component';
import { TasksOnTheProjectTableComponent } from './components/tasks-on-the-project-table/tasks-on-the-project-table.component';
import { FileModule } from '../file/file.module';
import { AddFileModalComponent } from '../file/component/add-file-modal/add-file-modal.component';




@NgModule({
  declarations: [      
    TasksComponent,
    TaskDetailComponent,
    NewTaskModalComponent,
    TasksOnTheProjectTableComponent     
  ],
  imports: [
    FileModule,
    CommonModule,
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BsDatepickerModule.forRoot(),
    PaginationModule.forRoot(), 
  ],
  exports: [TasksComponent, TaskDetailComponent, TasksOnTheProjectTableComponent],
  entryComponents:[NewTaskModalComponent, AddFileModalComponent]
  
})
export class TaskModule { }
