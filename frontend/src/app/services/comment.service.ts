import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from '../modules/task/models/Comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }  

  getAllByTaskId(id: string, currentPage: number, pageSize: number, sort: string ): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/api/v1/comments/byTask/${id}?page=${currentPage}&size=${pageSize}&sort=${sort}`);
  }

  saveComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(`${environment.apiUrl}/api/v1/comments`, comment);
  }  
}
