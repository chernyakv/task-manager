import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { AlertService } from 'ngx-alerts';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {

  isProjectManager = false ;
   
  constructor(private authenticationService: AuthenticationService,
    private alertService: AlertService) { }

  ngOnInit() {
    if(this.authenticationService.currentUsersRole == "PROJECT_MANAGER") {
      this.isProjectManager = true;
    }   
  }

}
