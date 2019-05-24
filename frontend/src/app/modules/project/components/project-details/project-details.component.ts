import { Component, OnInit } from '@angular/core';
import { Project } from '../../models/Project';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from 'src/app/services/project.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { NewTaskModalComponent } from 'src/app/modules/task/components/new-task-modal/new-task-modal.component';
import { BehaviorSubject } from 'rxjs';
import { Task } from 'src/app/modules/task/models/Task';
import { TaskService } from 'src/app/services/task.service';
import { AuthService } from 'src/app/services/authentication.service';
import { NewProjectModalComponent } from '../new-project-modal/new-project-modal.component';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.less']
})
export class ProjectDetailsComponent implements OnInit {

  public modalRef: BsModalRef; 
  project: Project;  
  ready = false;
  isMyProject = false;
  projectId;
  

  constructor(private activateRoute: ActivatedRoute,
              private projectService: ProjectService,
              private modalService: BsModalService,
              private tasksService: TaskService,
              private authService: AuthService,
              private router: Router) {
  }  

  ngOnInit() {
    this.projectId = this.activateRoute.snapshot.params['id']; 
    this.updateProject();        
  }

  updateProject() {
    this.projectService.getById(this.projectId).subscribe(data => {  
      this.project = data;
      this.isMyProject = this.authService.currentUsername === this.project.manager ? true : false;
      this.ready = true;        
    });
  }

  _onDeleteProjectClick() {
    this.projectService.deleteProject(this.projectId).subscribe(() => {
      this.router.navigate(['']);
     })
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
  } 
}
