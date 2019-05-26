import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError, merge } from 'rxjs';
import { catchError, mergeMap } from 'rxjs/operators';
import { AuthService } from 'src/app/services/authentication.service';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { AlertService } from 'ngx-alerts';




@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService,
        private router: Router,
        private alertService: AlertService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError((err : HttpErrorResponse) => {
            if ([401, 403].indexOf(err.status) !== -1) {   
                if(request.url === `${environment.apiUrl}/token/refresh-token`) {
                    this.authService.logout();
                    this.router.navigate(['/login']);
                } else {
                    if(err.error.message != 'Incorrect username or password.') {
                        return this.authService.refreshToken(this.authService.tokenValue.refreshToken).pipe(
                            mergeMap(() => {
                                const secRequest = this.cloneRequestAndAddHeader(request);
                                return next.handle(secRequest);
                            })
                        )
                    }                   
                }    
            }
            if ([500].indexOf(err.status) !== -1) {    
                console.log(err)   
                this.alertService.danger(err.error.message);
            }
            if ([400].indexOf(err.status) !== -1) {    
                console.log(err);  ; 
                this.alertService.danger('test');
            }
            const error = err.error.message || err.statusText;
            return throwError(error);
        }));
    }

    cloneRequestAndAddHeader(request) {
        return request = request.clone({
            headers: request.headers.set('Authorization', 'Bearer ' + this.authService.tokenValue.token)
        })
    }
}
