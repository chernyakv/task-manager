import { Component, OnInit, Input } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { TaskService } from 'src/app/services/task.service';
import { Task } from '../../models/Task';
import { Observable } from 'rxjs';
import { NewTaskModalComponent } from '../new-task-modal/new-task-modal.component';
import { Project } from 'src/app/modules/project/models/Project';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks-on-the-project-table',
  templateUrl: './tasks-on-the-project-table.component.html',
  styleUrls: ['./tasks-on-the-project-table.component.less']
})
export class TasksOnTheProjectTableComponent implements OnInit {

  @Input() project: Project;

  public tasks: Task[];
  public pageSize = 13;
  public currentPage = 0;
  public totalItems = 0;
  public sort = "id";
  public direction = 'asc';
  public isAscDirection = true; 

  constructor(
    private router: Router,
    private tasksService: TaskService
  ) {}

  ngOnInit() { 
    this.updateTasks();
  }  

  pageChanged(event: any): void {    
    this.currentPage = event.page - 1;    
    this.updateTasks();    
  }

  updateTasks(){
    this.direction = this.isAscDirection ? 'asc' : 'desc';
    this.tasksService.getAllByProjectId(this.project.id, this.currentPage, this.pageSize, this.sort, this.direction).subscribe(data=>{
      this.tasks = data.content;
      this.totalItems = data.totalElements;
    })
  }

  onSortClick(sort: string) {         
    this.isAscDirection = sort === this.sort ? !this.isAscDirection : true;
    this.sort = sort;
    this.currentPage = 0;
    this.updateTasks();
  }

  onTaskClick(taskId: string) {
    this.router.navigate(['/task-details', taskId]);
  }
}
