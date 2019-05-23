import { Component, OnInit } from '@angular/core';
import { Project } from '../../models/Project';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from 'src/app/services/project.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { NewTaskModalComponent } from 'src/app/modules/task/components/new-task-modal/new-task-modal.component';
import { BehaviorSubject } from 'rxjs';
import { Task } from 'src/app/modules/task/models/Task';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.less']
})
export class ProjectDetailsComponent implements OnInit {

  public modalRef: BsModalRef; 
  project: Project;  
  ready: boolean = false;
  

  constructor(private activateRoute: ActivatedRoute,
              private projectService: ProjectService,
              private modalService: BsModalService,
              private tasksService: TaskService) {
  }  

  ngOnInit() {

    const id = this.activateRoute.snapshot.params['id'];

    this.projectService.getById(id).subscribe(data => {  
      this.project = data;
      this.ready = true;   
         
    },
    error => {
      console.log('error');
    });    

    
  }

  _openTcurrentPageaskModal() {
    this.modalRef = this.modalService.show(NewTaskModalComponent);
    this.modalRef.content.currentProject = this.project; 
  }

  _openTaskModal() {    
    const initialState = {     
      projectId: this.project.id
    };
    this.modalRef = this.modalService.show(NewTaskModalComponent, {initialState});
    this.modalRef.content.project = this.project;    
  }

 
}
