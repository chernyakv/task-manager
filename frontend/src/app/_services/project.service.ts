import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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


}
