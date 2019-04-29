import { Component, OnInit, TemplateRef } from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


import { AuthenticationService } from 'src/app/_services/authentication.service';
import { User } from 'src/app/modules/user/models/User';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NewProjectModalComponent } from 'src/app/modules/project/components/new-project-modal/new-project-modal.component';
import { Observable } from 'rxjs';
import { Task } from '../../models/Task';
import { TaskService } from 'src/app/_services/task.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.less']
})
export class TasksComponent implements OnInit { 

  public tasks: Observable<Task[]>;
  public modalRef: BsModalRef;
  public currentUser: string;
  public username: string;

  constructor(
    private modalService: BsModalService,
    private authenticationService: AuthenticationService,
    private tasksService: TaskService
  ) {}

  ngOnInit() {     
    this.tasks = this.tasksService.getAllByUsername(this.authenticationService.currentUsername);
  } 

 

  public _closeModal(){
    this.modalRef.hide();
  }  
}
