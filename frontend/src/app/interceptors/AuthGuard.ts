import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/authentication.service';


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authService: AuthService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        const token = this.authService.tokenValue;

        if (token) {
            const role = this.authService.currentUsersRole;


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
