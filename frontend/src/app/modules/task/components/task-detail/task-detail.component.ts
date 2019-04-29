import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/Task';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from 'src/app/_services/task.service';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.less']
})
export class TaskDetailComponent implements OnInit {

  task: Task;
  ready: boolean = false;

  constructor(private activateRoute: ActivatedRoute,
    private taskService: TaskService) {
    
  } 

  ngOnInit() {
    const id = this.activateRoute.snapshot.params['id'];

    this.taskService.getById(id).subscribe(data => {  
      this.task = data;
      this.ready = true;        
    },
    error => {
      console.log('error');
    }); 
  }

  _inProgressClick(){
    this.task.taskStatus = "IN_PROGRESS";
    this.taskService.updateTask(this.task).subscribe(() => {
     
    });
  }

}
