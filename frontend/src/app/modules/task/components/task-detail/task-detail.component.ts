import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/Task';
import { Comment } from '../../models/Comment';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from 'src/app/_services/task.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CommentService } from 'src/app/_services/comment.service';
import { ProjectService } from 'src/app/_services/project.service';
import { Project } from 'src/app/modules/project/models/Project';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { AddFileModalComponent } from 'src/app/modules/file/component/add-file-modal/add-file-modal.component';
import { projectionDef } from '@angular/core/src/render3';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.less']
})
export class TaskDetailComponent implements OnInit {

  task: Task;
  project: Project;
  public modalRef: BsModalRef;
  ready: boolean = false;
  role: string;
  itMyTask: boolean;
  comments: Comment[];
  text: string;
  newComment: Comment;

  constructor(private activateRoute: ActivatedRoute,
    private commentService: CommentService,
    private projectService: ProjectService,
    private taskService: TaskService,
    private authenticationService: AuthenticationService,
    private modalService: BsModalService,) {
    
  } 

  ngOnInit() {
    const id = this.activateRoute.snapshot.params['id'];

    this.taskService.getById(id).subscribe(data => {  
      this.task = data;
      this.itMyTask = this.authenticationService.currentUsername == this.task.assignee ? true : false;
      this.updateComments();
      this.projectService.getById(this.task.projectId).subscribe(data => {
        this.project = data;
        this.ready = true;  
      })            
    },
    error => {
      console.log('error');
    }); 

   
    

    this.role = this.authenticationService.currentUsersRole;    
  }

  updateComments() {
    this.commentService.getAllByTaskId(this.task.id, 0, 10, "id").subscribe(data => {
      this.comments = data.content;
      console.log(this.comments);
    })
  }

  _inProgressClick(){
    this.task.taskStatus = "IN_PROGRESS";
    this.taskService.updateTask(this.task).subscribe(() => {     
    });
  }

  _readyForTestClick(){
    this.task.taskStatus = "READY_FOR_TEST";
    this.task.assignee = "che";
    this.itMyTask = this.authenticationService.currentUsername == this.task.assignee ? true : false;
    this.taskService.updateTask(this.task).subscribe( data => {         
    });
  }

  _closedClick(){
    this.task.taskStatus = "CLOSED";
    this.taskService.updateTask(this.task).subscribe(() => {     
    });
  }

  _submitClick(){
    this.newComment = new Comment(); 
    this.newComment.author = this.authenticationService.currentUsername;
    this.newComment.description = this.text;
    this.newComment.taskId = this.task.id;
    this.commentService.saveComment(this.newComment).subscribe(()=>{

    })
    this.updateComments();
  }

  _openFileModal() {  
    
    const initialState = {     
      taskId: this.task.id,
      projectId: this.task.projectId
    };
    this.modalRef = this.modalService.show(AddFileModalComponent, {initialState});      
  }

}
