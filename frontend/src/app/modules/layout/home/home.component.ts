import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from 'src/app/services/authentication.service';
import { AlertService } from 'ngx-alerts';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {

  @Output() projectUpdated = new EventEmitter()
  isProjectManager = false ;  
   
  constructor(private authService: AuthService,
    private alertService: AlertService) { }

  ngOnInit() {
    if(this.authService.currentUsersRole == "PROJECT_MANAGER") {
      this.isProjectManager = true;
    }   
  }
  onNewProjectClick(){ 
    this.projectUpdated.emit();         
  }


}
