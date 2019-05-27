import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../modules/user/models/User';



@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient) { }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}/api/v1/users/username/${username}`);
  }

  getAllUsers(currentPage: number, pageSize: number, sort: string ): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/api/v1/users/?page=${currentPage}&size=${pageSize}&sort=${sort}`);
  }

  getAllByProjectId(id: string, roles: string[], currentPage: number, pageSize: number, sort: string ): Observable<any> {
    let params = new HttpParams()
      .set('page', `${currentPage}`)
      .set('size', `${pageSize}`)
      .set('sort', `${sort}`);
    roles.forEach((role) => params = params.append('roles', '' + role));
    return this.http.get<any>(`${environment.apiUrl}/api/v1/users/byProject/${id}`, {params});
  }

  getAllWithoutProject(currentPage: number, pageSize: number, sort: string ): Observable<any> {
    let params = new HttpParams()
    .set('page', `${currentPage}`)
    .set('size', `${pageSize}`)
    .set('sort', `${sort}`);
    params = params.append('roles', '' + 'DEVELOPER');
    params = params.append('roles', '' + 'TESTER');
    return this.http.get<any>(`${environment.apiUrl}/api/v1/users/withoutProject`, {params});
  }  

  deleteUser(id: string): Observable<void> {    
    return this.http.delete<void>(`${environment.apiUrl}/api/v1/users/` + id);    
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}/api/v1/users`, user);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${environment.apiUrl}/api/v1/users`, user);
  }


}
