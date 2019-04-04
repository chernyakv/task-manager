import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient) { }

  login(loginPayLoad): Observable<Object> {
    return this.http.post('http://localhost:8080/api/login', loginPayLoad);
  }


}
