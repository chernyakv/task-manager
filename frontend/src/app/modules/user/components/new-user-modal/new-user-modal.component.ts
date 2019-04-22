import { Component, OnInit, Input } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../../models/User';
import { UrlSegment } from '@angular/router';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-new-user-modal',
  templateUrl: './new-user-modal.component.html',
  styleUrls: ['./new-user-modal.component.less']
})
export class NewUserModalComponent implements OnInit {

  editableUser: User = new User();  
  newUserForm : FormGroup;
  submmited = false;

  constructor(private formBuilder: FormBuilder,
    private modalRef: BsModalRef,
    private userService: UserService) {       

  }

  ngOnInit() {
    if(this.editableUser){
      this.editableUser = new User();
    } else {      
      this.editableUser = new User();
    }
    
    this.newUserForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      
      role: ['']
    })
    
  }

  get f() {
    return this.newUserForm.controls;
  }

  _onSubmit() {
    this.editableUser.role = this.newUserForm.controls.role.value;
    console.log(this.editableUser);
    this.userService.saveUser(this.editableUser).subscribe(() => {
      
    });
    this.submmited = true;
    this.modalRef.hide();
  }

}
