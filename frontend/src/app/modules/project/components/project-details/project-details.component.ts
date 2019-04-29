import { Component, OnInit } from '@angular/core';
import { Project } from '../../models/Project';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from 'src/app/_services/project.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { NewTaskModalComponent } from 'src/app/modules/task/components/new-task-modal/new-task-modal.component';

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
              private modalService: BsModalService) {
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

  _openTaskModal() {
    this.modalRef = this.modalService.show(NewTaskModalComponent);
    this.modalRef.content.currentProject = this.project; 
  }



  

}
