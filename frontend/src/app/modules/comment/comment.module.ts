import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommentsListComponent } from './comments-list/comments-list.component';

@NgModule({
  declarations: [CommentsListComponent],
  imports: [
    CommonModule
  ],

  exports: [CommentsListComponent],
})
export class CommentModule { }
