import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {

  isProjectManager = false ;
   
  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    if(this.authenticationService.currentUsersRole == "PROJECT_MANAGER") {
      this.isProjectManager = true;
    }
  }

}
