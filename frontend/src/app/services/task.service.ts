import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../modules/user/models/User';
import { Task } from '../modules/task/models/Task';
import { map } from 'rxjs/operators';



@Injectable({ providedIn: 'root' })
export class TaskService {

  constructor(private http: HttpClient) { }

  getAllByUsername(username: String, currentPage: number, pageSize: number, sort: string, order: string): Observable<any> {
    let params = new HttpParams()
      .set('page', `${currentPage}`)
      .set('size', `${pageSize}`)
      .set('sort', `${sort}`)
      .set('order', `${order}`);
    return this.http.get<any>(`${environment.apiUrl}/api/v1/tasks/byUsername/${username}`, {params});     
  }  

  getAllByProjectId(projectId: String, currentPage: number, pageSize: number, sort: string, order: string): Observable<any> {
    let params = new HttpParams()
      .set('page', `${currentPage}`)
      .set('size', `${pageSize}`)
      .set('sort', `${sort}`)
      .set('order', `${order}`);
    return this.http.get<any>(`${environment.apiUrl}/api/v1/tasks/byProject/${projectId}`, {params});
  }

  getById(id: string): Observable<Task> {    
    return this.http.get<Task>(`${environment.apiUrl}/api/v1/tasks/` + id);    
  }

  saveTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${environment.apiUrl}/api/v1/tasks`, task);
  }

  updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${environment.apiUrl}/api/v1/tasks`, task);
  }
}
