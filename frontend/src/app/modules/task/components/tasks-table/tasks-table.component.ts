import { Component, OnInit, EventEmitter, TemplateRef, Input, Output } from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/authentication.service';
import { User } from 'src/app/modules/user/models/User';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NewProjectModalComponent } from 'src/app/modules/project/components/new-project-modal/new-project-modal.component';
import { Observable } from 'rxjs';
import { Task } from '../../models/Task';

import { Router } from '@angular/router';
import { TaskService } from 'src/app/services/task.service';


@Component({
  selector: 'app-tasks-table',
  templateUrl: './tasks-table.component.html',
  styleUrls: ['./tasks-table.component.less']
})
export class TasksTableComponent implements OnInit {

  public tasks: Task[]; 
  public checkTasks = false;

  public pageSize = 8;
  public currentPage = 0;
  public totalItems = 0;
  public sort = "id";
  public direction = 'asc';
  public isAscDirection = true;

  constructor(
    private router: Router,
    private authService: AuthService,
    private tasksService: TaskService
  ) {}

  ngOnInit() {
    this.updateTasks();   
  }

  updateTasks() {
    this.direction = this.isAscDirection ? 'asc' : 'desc';
    this.tasksService.getAllByUsername(this.authService.currentUsername, this.currentPage, this.pageSize, this.sort, this.direction).subscribe(data => {
      this.tasks = data.content;     
      this.totalItems = data.totalElements;
      this.checkTasks = this.tasks.length > 0 ? true : false;      
    });
  }

  onPageChanged(event: any): void {    
    this.currentPage = event.page - 1;
    this.updateTasks();
  } 

  onSortClick(sort: string) {         
    this.isAscDirection = sort === this.sort ? !this.isAscDirection : true;
    this.sort = sort;
    this.currentPage = 0;
    this.updateTasks();
  }

  onTaskClick(taskId: string) {
    this.router.navigate(['/task-details', taskId]);
  }
}
