import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;
  username: string;
  
  constructor(
    private router: Router,
    private authService: AuthService) { }

  ngOnInit() { 
     
  }

  public isLoggedTest() {
    const token = this.authService.tokenValue;
    if (token) {
        this.username = this.authService.currentUsername; 
        this.isLogged = true;  
        return true;
    } 
    return false; 
  }

  public logOut(){    
    this.authService.logout(); 
    this.isLogged = false;  
    location.reload(true);
  }

  public viewUserProfile() {
    this.router.navigate(['/user-details', this.authService.currentUsername]);
  }
}
