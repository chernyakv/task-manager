import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';


import { environment } from 'src/environments/environment';
import { User } from '../modules/user/models/User';
import { JwtToken } from '../modules/user/models/jwt-token';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private tokenSubject: BehaviorSubject<JwtToken>;
    public token: Observable<JwtToken>;

    constructor(private http: HttpClient) {
        this.tokenSubject = new BehaviorSubject<JwtToken>(JSON.parse(localStorage.getItem('token')));
        this.token = this.tokenSubject.asObservable();
    }

    public get tokenValue(): JwtToken {
        return this.tokenSubject.value;
    }

    public get currentUsername(): string {
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(this.tokenSubject.value.token.toString());        
        return decodedToken.sub;          
    }

    login(username: string, password: string) {
        return this.http.post<any>(`${environment.apiUrl}/token/generate-token`, { username, password })
            .pipe(map(token => {
                
                if (token) {                    
                    localStorage.setItem('token', JSON.stringify(token));
                    this.tokenSubject.next(token);
                }

                return token;
            }));
    }

    logout() {
              
        localStorage.removeItem('token');
        this.tokenSubject.next(null);
       
    }
}
