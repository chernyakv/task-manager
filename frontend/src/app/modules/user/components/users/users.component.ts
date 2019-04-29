import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {  } from 'rxjs/operators';

import { User } from '../../models/User';
import { UserService } from 'src/app/_services/user.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { NewUserModalComponent } from '../new-user-modal/new-user-modal.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.less']
})
export class UsersComponent implements OnInit {

  
  public modalRef: BsModalRef;
  public users: Observable<User[]>;

  public pageSize = 8;
  public currentPage = 0;
  public totalItems = 9;

  isProjectManager = true;


  constructor(private userService: UserService,
    private modalService: BsModalService) { }

  
  public _openUserModal(user: User){        
    this.modalRef = this.modalService.show(NewUserModalComponent);
    //this.modalRef.content.editableUser = user;    
  }

  public _testLog(user: User) {
    console.log(user.username);
  }

  updateUsesr() {
    this.users = this.userService.getUsersPage(this.currentPage,this.pageSize,"TEST");    
  }

  deleteUser(id:string) {
    if(confirm("Are you sure you want to delete this ?")){      
      this.userService.deleteUser(id).subscribe((res) => {
        this.updateUsesr();
      });;
    }
  }  

  pageChanged(event: any): void {    
    this.currentPage = event.page - 1;    
    this.updateUsesr();    
  }

  ngOnInit() {
    this.updateUsesr();
  }

}
