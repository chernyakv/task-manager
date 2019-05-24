import { Component, OnInit, Input } from '@angular/core';
import { TaskDetailComponent } from '../../task/components/task-detail/task-detail.component';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-comments-list',
  templateUrl: './comments-list.component.html',
  styleUrls: ['./comments-list.component.less']
})
export class CommentsListComponent implements OnInit {

  @Input() taskId;
  comments: Comment[];


  constructor(private commentService: CommentService) { }

  ngOnInit() {
    this.updateComments();
  }

  updateComments() {
    this.commentService.getAllByTaskId(this.taskId, 0, 10, "id").subscribe(data => {
      this.comments = data.content;
      console.log(this.comments);
    })
  }
}
