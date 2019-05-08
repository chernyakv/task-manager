import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../modules/user/models/User';
import { Task } from '../modules/task/models/Task';



@Injectable({ providedIn: 'root' })
export class TaskService {

  constructor(private http: HttpClient) { }

  getAllByUsername(username: String, currentPage: number, pageSize: number, sort: string): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/api/v1/tasks/byUsername/${username}?page=${currentPage}&size=${pageSize}&sort=${sort}`);
  }  

  getAllByProjectId(projectId: String, currentPage: number, pageSize: number, sort: string): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/api/v1/tasks/byProject/${projectId}?page=${currentPage}&size=${pageSize}&sort=${sort}`);
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
