import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-user-modal',
  templateUrl: './new-user-modal.component.html',
  styleUrls: ['./new-user-modal.component.less']
})
export class NewUserModalComponent implements OnInit {

  newUserForm : FormGroup;
  submmited = false;

  constructor(private formBuilder: FormBuilder,
    private modalRef: BsModalRef) { 

  }

  ngOnInit() {
    this.newUserForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  get f() {
    return this.newUserForm.controls;
  }

  _onSubmit() {
    this.submmited = true;
  }

}
