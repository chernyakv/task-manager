import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';


import { environment } from 'src/environments/environment';
import { User } from '../modules/user/models/User';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<String>;
    public currentUser: Observable<String>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<String>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): String {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`${environment.apiUrl}/token/generate-token`, { username, password })
            .pipe(map(user => {
                
                if (user) {
                    
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                }

                return user;
            }));
    }

    logout() {
              
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
       
    }
}
