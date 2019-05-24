import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError, merge } from 'rxjs';
import { catchError, mergeMap } from 'rxjs/operators';
import { AuthService } from 'src/app/services/authentication.service';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';




@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService,
        private router: Router) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if ([401, 403].indexOf(err.status) !== -1) {                 
                if(request.url === `${environment.apiUrl}/token/refresh-token`) {
                    this.authService.logout();
                    this.router.navigate(['/login']);
                } else {
                    return this.authService.refreshToken(this.authService.tokenValue.refreshToken).pipe(
                        mergeMap(() => {
                            const secRequest = this.cloneRequestAndAddHeader(request);
                            return next.handle(secRequest);
                        })
                    )
                }    
            }
            const error = err.error.message || err.statusText;
            console.log(error);
            return throwError(error);
        }));
    }

    cloneRequestAndAddHeader(request) {
        return request = request.clone({
            headers: request.headers.set('Authorization', 'Bearer ' + this.authService.tokenValue.token)
        })
    }
}
