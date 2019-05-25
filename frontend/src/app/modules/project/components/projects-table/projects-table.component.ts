import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../../models/Project';
import { ProjectService } from 'src/app/services/project.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { NewProjectModalComponent } from '../new-project-modal/new-project-modal.component';

@Component({
  selector: 'app-projects-table',
  templateUrl: './projects-table.component.html',
  styleUrls: ['./projects-table.component.less']
})
export class ProjectsTableComponent implements OnInit {

  public projects: Project[];
  public modalRef: BsModalRef;
  
  public pageSize = 8;
  public currentPage = 0;
  public totalItems = 0;
  public sort = "id";
  public direction = 'asc';
  public isAscDirection = true;

  constructor(private projectService: ProjectService,
    private modalService: BsModalService,) { }

  ngOnInit() {
    this.updateProjects();
  }

  pageChanged(event: any): void {    
    this.currentPage = event.page - 1;    
    this.updateProjects();    
  }

  updateProjects() {
    this.projectService.getProjectsPage(this.currentPage, this.pageSize, this.sort, this.direction).subscribe(data => {
      this.direction = this.isAscDirection ? 'asc' : 'desc';
      this.projects = data.content;
      this.totalItems = data.totalElements;
    })
  }

  _deleteProject(id:string) {
    if(confirm("Are you sure you want to delete this ?")){      
      this.projectService.deleteProject(id).subscribe((res) => {
        this.updateProjects();
      });;
    }
  }

  onSortClick(sort: string) {         
    this.isAscDirection = sort === this.sort ? !this.isAscDirection : true;
    this.sort = sort;
    this.currentPage = 0;
    this.updateProjects();
  }

  public _openProjectModal(){   
    this.modalRef = this.modalService.show(NewProjectModalComponent);      
  }

}
