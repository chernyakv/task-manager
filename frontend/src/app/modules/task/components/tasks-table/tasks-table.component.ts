import { Component, OnInit, EventEmitter, TemplateRef, Input, Output } from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
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
  public modalRef: BsModalRef;
  public currentUser: string;
  public username: string;

  public pageSize = 8;
  public currentPage = 0;
  public totalItems = 0;

  constructor(
    private router: Router,
    private modalService: BsModalService,
    private authenticationService: AuthenticationService,
    private tasksService: TaskService
  ) {}

  ngOnInit() {
    this.updateTasks();   
  }

  pageChanged(event: any): void {    
    this.currentPage = event.page - 1;
    this.updateTasks();
  }

  updateTasks() {
    this.tasksService.getAllByUsername(this.authenticationService.currentUsername, this.currentPage, this.pageSize, 'id').subscribe(data => {
      this.tasks = data.content;
      this.totalItems = data.totalElements;  
    });
  }

  taskOpen(taskId: string) {
    this.router.navigate(['/task-details', taskId]);
  }

  public _closeModal() {
    this.modalRef.hide();
  }
}
