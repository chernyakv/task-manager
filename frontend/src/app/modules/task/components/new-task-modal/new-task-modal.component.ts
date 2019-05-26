import { Component, OnInit, Output, EventEmitter } from '@angular/core';
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

  @Output() taskUpdated = new EventEmitter();
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
        dueDate: [new Date(Number(this.task.dueDate)), Validators.required],
        estimation: [Number(this.task.estimation), Validators.required],
        assignee: [this.task.assignee],
        priority: [this.task.priority]
      });
    } else {
      this.task = new Task();
      this.newTaskForm = this.formBuilder.group({
        title: ['', Validators.required],
        description: ['', Validators.required],
        dueDate: ['', Validators.required],
        estimation: ['', Validators.required],
        assignee: [''],
        priority: ['']
      });
    }
  }

  get f() {
    return this.newTaskForm.controls;
  }

  onSubmit() {
    this.submmited = true;
    console.log(this.f.assignee.value);
    this.task.title = this.f.title.value;
    this.task.description = this.f.description.value;
    this.task.dueDate = this.f.dueDate.value.getTime();    
    this.task.priority = this.f.priority.value;
    this.task.assignee = this.f.assignee.value === 'toMe' ? this.authService.currentUsername : this.f.assignee.value ;    
    this.task.estimation = this.f.estimation.value;
    this.task.projectId = this.projectId;
    this.task.reporter = this.editMode ? this.task.reporter : this.authService.currentUsername;    
    this.task.taskStatus = this.editMode ? this.task.taskStatus : 'OPEN';
    this.taskService.saveTask(this.task).subscribe(() => {
      this.taskUpdated.emit();
      const alertsMessage = this.editMode ? 'Task has been updated' : 'Task created';
      this.alertService.success(alertsMessage);
      this.modalRef.hide();
    });
  }

  onClose(): void {
    this.modalRef.hide();
  }
}
