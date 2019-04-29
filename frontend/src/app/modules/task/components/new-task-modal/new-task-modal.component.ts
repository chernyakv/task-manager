import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/_services/user.service';
import { BsModalRef } from 'ngx-bootstrap';
import { User } from 'src/app/modules/user/models/User';
import { Observable } from 'rxjs';
import { Task } from '../../models/Task';
import { Project } from 'src/app/modules/project/models/Project';
import { TaskService } from 'src/app/_services/task.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';


@Component({
  selector: 'app-new-task-modal',
  templateUrl: './new-task-modal.component.html',
  styleUrls: ['./new-task-modal.component.less']
})
export class NewTaskModalComponent implements OnInit {

  projectId: any;  
  users: Observable<User[]>;  
  task: Task; 
  newTaskForm : FormGroup;
  submmited = false;

  constructor(private formBuilder: FormBuilder,
    private modalRef: BsModalRef,
    private userService: UserService,
    private taskService: TaskService,
    private authenticationService: AuthenticationService) {       

  }

  ngOnInit() {    
    this.users = this.userService.getByProjectId(this.projectId);   
    this.task = new Task();

    this.newTaskForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      dueDate: ['', Validators.required], 
      assignee: [''], 
      priority: ['']
    })
    
  }

  get f() {
    return this.newTaskForm.controls;
  }

  _onSubmit() {    
    
    this.task.dueDate = this.f.dueDate.value.getTime();
    this.task.taskStatus = 'OPEN';
    this.task.priority = this.f.priority.value;
    this.task.assignee = this.f.assignee.value;
    this.task.reporter = this.authenticationService.currentUsername;
    this.task.estimation = this.f.dueDate.value.getTime();
    this.task.projectId = this.projectId;   
    
    this.taskService.saveTask(this.task).subscribe(() => {
      this.submmited = true;   
      this.modalRef.hide();
    });
    
  }

}
