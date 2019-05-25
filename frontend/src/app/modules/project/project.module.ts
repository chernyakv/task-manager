import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewProjectModalComponent } from './components/new-project-modal/new-project-modal.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ProjectsTableComponent } from './components/projects-table/projects-table.component';
import { PaginationModule, TabsModule, TypeaheadModule } from 'ngx-bootstrap';
import { ProjectDetailsComponent } from './components/project-details/project-details.component';
import { UserModule } from '../user/user.module';
import { TaskModule } from '../task/task.module';
import { NewTaskModalComponent } from '../task/components/new-task-modal/new-task-modal.component';

@NgModule({
  declarations: [
    NewProjectModalComponent,
    ProjectsTableComponent,
    ProjectDetailsComponent
  ],
  imports: [
    TaskModule,
    UserModule,
    CommonModule,
    ReactiveFormsModule,   
    FormsModule,
    BrowserModule,
    RouterModule,  
    HttpClientModule,
    PaginationModule.forRoot(),
    TabsModule.forRoot(),
    TypeaheadModule.forRoot()    
  ],
  exports: [ProjectsTableComponent],
  entryComponents: [
    NewProjectModalComponent,
    NewTaskModalComponent
  ]
})
export class ProjectModule { }
