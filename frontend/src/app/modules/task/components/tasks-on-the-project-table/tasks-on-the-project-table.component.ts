import { Component, OnInit, Input } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { TaskService } from 'src/app/_services/task.service';
import { Task } from '../../models/Task';
import { Observable } from 'rxjs';
import { NewTaskModalComponent } from '../new-task-modal/new-task-modal.component';
import { Project } from 'src/app/modules/project/models/Project';

@Component({
  selector: 'app-tasks-on-the-project-table',
  templateUrl: './tasks-on-the-project-table.component.html',
  styleUrls: ['./tasks-on-the-project-table.component.less']
})
export class TasksOnTheProjectTableComponent implements OnInit {

  @Input() project: Project;

  public tasks: Task[];
  public modalRef: BsModalRef;

  public pageSize = 10;
  public currentPage = 0;
  public totalItems = 0;
  

  constructor(
    private modalService: BsModalService,
    private authenticationService: AuthenticationService,
    private tasksService: TaskService
  ) {}

  ngOnInit() { 
    this.updateTasks();
  }  

  public logOut(){    
    this.authenticationService.logout();  
    location.reload(true);
  } 

  _openTaskModal() {    
    const initialState = {     
      projectId: this.project.id
    };
    this.modalRef = this.modalService.show(NewTaskModalComponent, {initialState});
    this.modalRef.content.project = this.project;    
  }

  public _closeModal(){
    this.modalRef.hide();
  }

  pageChanged(event: any): void {    
    this.currentPage = event.page - 1;    
    this.updateTasks();    
  }

  updateTasks(){
    this.tasksService.getAllByProjectId(this.project.id, this.currentPage, this.pageSize, 'id').subscribe(data=>{
      this.tasks = data.content;
      this.totalItems = data.totalElements;
    })

  }

}
