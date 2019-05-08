import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/_services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;
  username: string;
  
  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() { 
     
  }

  public isLoggedTest() {
    const token = this.authenticationService.tokenValue;
    if (token) {
        this.username = this.authenticationService.currentUsername; 
        this.isLogged = true;  
        return true;
    } 
    return false; 
  }

  public logOut(){    
    this.authenticationService.logout(); 
    this.isLogged = false;  
    location.reload(true);
  }
}
