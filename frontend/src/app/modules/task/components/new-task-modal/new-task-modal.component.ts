import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { BsModalRef } from 'ngx-bootstrap';
import { User } from 'src/app/modules/user/models/User';
import { Observable } from 'rxjs';
import { Task } from '../../models/Task';
import { Project } from 'src/app/modules/project/models/Project';
import { TaskService } from 'src/app/services/task.service';
import { AuthService } from 'src/app/services/authentication.service';
import { AlertService } from 'ngx-alerts';


@Component({
  selector: 'app-new-task-modal',
  templateUrl: './new-task-modal.component.html',
  styleUrls: ['./new-task-modal.component.less']
})
export class NewTaskModalComponent implements OnInit {

  projectId: any;
  role;
  users: User[];
  task: Task;
  newTaskForm: FormGroup;
  submmited = false;
  editMode = false;

  constructor(private formBuilder: FormBuilder,
              private modalRef: BsModalRef,
              private userService: UserService,
              private taskService: TaskService,
              private authService: AuthService,
              private alertService: AlertService) {

  }

  ngOnInit() {  
    this.role = this.authService.currentUsersRole === 'PROJECT_MANAGER';

    this.userService.getAllByProjectId(this.projectId,['DEVELOPER'], 0, 10, "id").subscribe(data => {
      this.users = data.content;
    });

    if(this.editMode) {
      this.newTaskForm = this.formBuilder.group({
        title: [this.task.title, Validators.required],
        description: [this.task.description, Validators.required],
        dueDate: [new Date(Number(this.task.dueDate))],
        assignee: [this.task.assignee],
        priority: [this.task.priority]
      });
    } else {
      this.task = new Task();
      this.newTaskForm = this.formBuilder.group({
        title: ['', Validators.required],
        description: ['', Validators.required],
        dueDate: ['', Validators.required],
        assignee: [''],
        priority: ['']
      });
    }
  }

  get f() {
    return this.newTaskForm.controls;
  }

  _onSubmit() {
    this.submmited = true;

    this.task.title = this.f.title.value;
    this.task.description = this.f.title.value;
    this.task.dueDate = this.f.dueDate.value.getTime();    
    this.task.priority = this.f.priority.value;
    this.task.assignee = this.f.assignee.value;    
    this.task.estimation = this.f.dueDate.value.getTime();
    this.task.projectId = this.projectId;
    this.task.reporter = this.editMode ? this.task.reporter : this.authService.currentUsername;    
    this.task.taskStatus = this.editMode ? this.task.taskStatus : 'OPEN';
    this.taskService.saveTask(this.task).subscribe(() => {
      const alertsMessage = this.editMode ? 'Task updated' : 'Task created';
      this.alertService.success(alertsMessage);
      this.modalRef.hide();
    });
  }

  _onClose(): void {
    this.modalRef.hide();
  }
}
