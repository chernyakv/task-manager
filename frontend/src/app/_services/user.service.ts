import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../modules/user/models/User';



@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${environment.apiUrl}/api/v1/users`);
  }

  getByProjectId(id: string): Observable<User[]> {
    return this.http.get<User[]>(`${environment.apiUrl}/api/v1/users/getByProjectId/` + id);
  }

  getUsersPage(currentPage: number, pageSize: number, sort: string): Observable<User[]> {
    return this.http.get<User[]>(`${environment.apiUrl}/api/v1/users/page?page=${currentPage}&size=${pageSize}&sort=${sort}`);
  }

  deleteUser(id: string): Observable<void> {    
    return this.http.delete<void>(`${environment.apiUrl}/api/v1/users/` + id);    
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}/api/v1/users`, user);
  }


}
