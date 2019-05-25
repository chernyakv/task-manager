import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../modules/user/models/User';
import { Project } from '../modules/project/models/Project';



@Injectable({ providedIn: 'root' })
export class ProjectService {

  constructor(private http: HttpClient) { } 

  createProject(project: Project): Observable<Project> {    
    return this.http.post<Project>(`${environment.apiUrl}/api/v1/projects`, project);
  }

  getAllProjects(): Observable<Project[]> {    
    return this.http.get<Project[]>(`${environment.apiUrl}/api/v1/projects`);
  } 

  getProjectsPage(currentPage: number, pageSize: number, sort: string, order: string): Observable<any> {
    let params = new HttpParams()
      .set('page', `${currentPage}`)
      .set('size', `${pageSize}`)
      .set('sort', `${sort}`)
      .set('order', `${order}`);
    return this.http.get<any>(`${environment.apiUrl}/api/v1/projects`, {params});
  }

  deleteProject(id: string): Observable<void> {    
    return this.http.delete<void>(`${environment.apiUrl}/api/v1/projects/` + id);    
  }

  getById(id: string): Observable<Project> {
    return this.http.get<Project>(`${environment.apiUrl}/api/v1/projects/` + id);
  }
}
