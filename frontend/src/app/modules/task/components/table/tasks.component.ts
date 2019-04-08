import { Component, OnInit, TemplateRef } from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NewUserModalComponent } from '../modals/new-user-modal/new-user-modal.component';
import { NewProjectModalComponent } from '../modals/new-project-modal/new-project-modal.component';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { User } from 'src/app/modules/user/models/User';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.less']
})
export class TasksComponent implements OnInit {
  
  public modalRef: BsModalRef;
  public currentUser: String;

  constructor(
    private modalService: BsModalService,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.currentUser = this.authenticationService.currentUserValue;
    console.log(this.currentUser);    
  }

  public _openUserModal(){   
    this.modalRef = this.modalService.show(NewUserModalComponent);
  }

  public _openProjectModal(){   
    this.modalRef = this.modalService.show(NewProjectModalComponent);
  }

  public onSubmit(){
    this.modalRef.hide();
    console.log("123");    
  }

  public logOut(){    
    this.authenticationService.logout();  
    location.reload(true);
  }
 

  public _closeModal(){
    this.modalRef.hide();
  }

  

  
}
