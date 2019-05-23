import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../../models/User';
import { UrlSegment } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { UserRole } from '../../models/UserRole';

@Component({
  selector: 'app-new-user-modal',
  templateUrl: './new-user-modal.component.html',
  styleUrls: ['./new-user-modal.component.less']
})
export class NewUserModalComponent implements OnInit {

  @Output() addUser: EventEmitter<User> = new EventEmitter<User>();
  roles = [];
  editableUser: User = new User();
  form : FormGroup;
  submmited = false;

  constructor(private formBuilder: FormBuilder,
              private modalRef: BsModalRef,
              private userService: UserService) {}

  ngOnInit() {
    for (const role in UserRole) {
      if (UserRole[role] !== UserRole.ADMIN) {
        this.roles.push(UserRole[role]);
      }    
    }
    console.log(this.roles[0]);

    if(this.editableUser){
      this.editableUser = new User();
    } else {
      this.editableUser = new User();
    }

    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['']
    });
  }

  get f() {
    return this.form.controls;
  }

  _onSubmit() {
    this.submmited = true;
    if(this.form.valid){
      this.editableUser.role = this.form.controls.role.value;
      this.addUser.emit(this.editableUser);
      this.modalRef.hide();
    }
  }

}
