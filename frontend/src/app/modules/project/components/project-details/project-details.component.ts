import { Component, OnInit, ViewChild } from '@angular/core';
import { Project } from '../../models/Project';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from 'src/app/services/project.service';
import { BsModalService, BsModalRef, TypeaheadMatch } from 'ngx-bootstrap';
import { NewTaskModalComponent } from 'src/app/modules/task/components/new-task-modal/new-task-modal.component';
import { BehaviorSubject } from 'rxjs';
import { Task } from 'src/app/modules/task/models/Task';
import { TaskService } from 'src/app/services/task.service';
import { AuthService } from 'src/app/services/authentication.service';
import { NewProjectModalComponent } from '../new-project-modal/new-project-modal.component';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { User } from 'src/app/modules/user/models/User';
import { UserService } from 'src/app/services/user.service';
import { UsersOnTheProjectTableComponent } from 'src/app/modules/user/components/users-on-the-project-table/users-on-the-project-table.component';
import { TasksOnTheProjectTableComponent } from 'src/app/modules/task/components/tasks-on-the-project-table/tasks-on-the-project-table.component';
import { AlertService } from 'ngx-alerts';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.less']
})
export class ProjectDetailsComponent implements OnInit {

  @ViewChild(UsersOnTheProjectTableComponent)
  private userTable: UsersOnTheProjectTableComponent;
  @ViewChild(TasksOnTheProjectTableComponent)
  private taskTable: TasksOnTheProjectTableComponent;

  modalRef: BsModalRef; 
  project: Project;  
  ready = false;
  isMyProject = false;
  projectId;

  asyncSelected: string;
  typeaheadLoading: boolean;
  typeaheadNoResults: boolean;
  isSelected = false;

  selectedUser: string;
  usersWithoutProject: User[];
  

  constructor(private activateRoute: ActivatedRoute,
              private projectService: ProjectService,
              private modalService: BsModalService,
              private tasksService: TaskService,
              private authService: AuthService,
              private router: Router,
              private userService: UserService,
              private alertService: AlertService) {
  }  

  ngOnInit() {
    this.projectId = this.activateRoute.snapshot.params['id']; 
    this.updateProject();
    
    this.userService.getAllWithoutProject(0, 100, 'id').subscribe(data => {
      this.usersWithoutProject = data.content;
    });
  }

  updateProject() {
    this.projectService.getById(this.projectId).subscribe(data => {  
      this.project = data;
      this.isMyProject = this.authService.currentUsername === this.project.manager ? true : false;
      this.ready = true;        
    },
    error=> {
      this.router.navigate(['']);
    });
    
  }

  _onDeleteProjectClick() {
    if(confirm("Are you sure you want to delete this ?")){    
      this.projectService.deleteProject(this.projectId).subscribe(() => {
        this.alertService.success('Project has been deleted');
        this.router.navigate(['']);     
       })    
    }    
  }

  _onEditProjectClick() {
    const initialState = {      
      project: this.project,
      editMode: true
    };
    this.modalRef = this.modalService.show(NewProjectModalComponent, {initialState}); 
    this.modalRef.content.projectUpdated.subscribe(() => {
      this.updateProject();
    })   
  }

  _openTaskModal() {    
    const initialState = {     
      projectId: this.project.id
    };
    this.modalRef = this.modalService.show(NewTaskModalComponent, {initialState});  
    this.modalRef.content.taskUpdated.subscribe(() => {
      this.taskTable.updateTasks();
    }) 
  }

  onInviteClick() {
    this.isSelected = false;
    this.userService.getUserByUsername(this.selectedUser).subscribe(data => {
      const user = data;
      user.projectId = this.project.id;
      this.userService.updateUser(user).subscribe(data => {
        this.userTable.updateUsers();
        this.alertService.success('User has been invited');
      });
    });
  }

  changeTypeaheadLoading(e: boolean): void {
    this.typeaheadLoading = e;
  }

  typeaheadOnSelect(e: TypeaheadMatch): void {
    this.isSelected = true;
  }
 
}
