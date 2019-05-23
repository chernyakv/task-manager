import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { } from 'rxjs/operators';

import { User } from '../../models/User';
import { UserService } from 'src/app/services/user.service';
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
  public currentPage = 1;
  public totalItems;

  isProjectManager = true;


  constructor(private userService: UserService,
    private modalService: BsModalService) { }

  ngOnInit() {
    this.updateUsers();
    
  }

  updateUsers() {
    this.userService.getAllUsers(this.currentPage - 1, this.pageSize, "id").subscribe(data => {
      this.users = data.content;
      
      this.totalItems = data.totalElements;
    })
  }  

  public _openUserModal(user: User) {
    this.modalRef = this.modalService.show(NewUserModalComponent);
    this.modalRef.content.addUser.subscribe(data => {
      this.userService.saveUser(data).subscribe(() => {
        this.updateUsers();
      });
    })
  }

  public _deleteUser(id: string) {
    if (confirm("Are you sure you want to delete this ?")) {
      this.userService.deleteUser(id).subscribe((res) => {
        this.updateUsers();
      });;
    }
  }

  public _pageChanged(event: any): void {
    this.currentPage = event.page;
    this.updateUsers();
  }
}
