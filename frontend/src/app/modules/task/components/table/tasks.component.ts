import { Component, OnInit, TemplateRef } from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


import { AuthenticationService } from 'src/app/_services/authentication.service';
import { User } from 'src/app/modules/user/models/User';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NewProjectModalComponent } from 'src/app/modules/project/components/new-project-modal/new-project-modal.component';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.less']
})
export class TasksComponent implements OnInit {
  
  public modalRef: BsModalRef;
  public currentUser: string;
  public username: string;

  constructor(
    private modalService: BsModalService,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    //this.currentUser = this.authenticationService.currentUserValue.token.toString();
    //const helper = new JwtHelperService();

    //const decodedToken = helper.decodeToken(this.currentUser);
    //this.username = decodedToken.sub;
       
  }  

  public _openProjectModal(){   
    this.modalRef = this.modalService.show(NewProjectModalComponent);
  }

  

 

  public logOut(){    
    this.authenticationService.logout();  
    location.reload(true);
  }
 

  public _closeModal(){
    this.modalRef.hide();
  }

  

  
}
