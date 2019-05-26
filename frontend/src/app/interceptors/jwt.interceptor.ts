import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/authentication.service';




@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const currentUser = this.authService.tokenValue;
        if (currentUser) {
            request = request.clone({
                headers: request.headers.set('Authorization', 'Bearer ' + currentUser.token)
            });
        }
        return next.handle(request);
    }
}
