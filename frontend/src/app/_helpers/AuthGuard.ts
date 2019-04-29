import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../_services/authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';



@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        
        const token = this.authenticationService.tokenValue;        
        
        if (token) {            
            const role = this.authenticationService.currentUsersRole;            
            
            if (route.data.roles && route.data.roles.indexOf(role) === -1) {
                this.router.navigate(['/login']);
                return false;
            }
            return true;  
        }       
        
        this.router.navigate(['/login']);
        return false;
    }
}
