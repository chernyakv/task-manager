import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/authentication.service';
import { User } from 'src/app/modules/user/models/User';
import { Task } from '../../models/Task';

@Component({
  selector: 'app-assignee-modal',
  templateUrl: './assignee-modal.component.html',
  styleUrls: ['./assignee-modal.component.less']
})
export class AssigneeModalComponent implements OnInit {

  @Output() assigneeSelected = new EventEmitter<string>();
  role: string;
  roles: string[] = [];
  projectId: any;
  users: User[];
 
  formGroup: FormGroup;
  submmited = false;

  constructor(private formBuilder: FormBuilder,
    private modalRef: BsModalRef,
    private userService: UserService,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.roles.push(this.role);
    this.userService.getAllByProjectId(this.projectId,this.roles , 0, 15, "id").subscribe(data => {
      this.users = data.content;
    });

    this.formGroup = this.formBuilder.group({
      assignee: ['', Validators.required]
    });
  }  

  get f() {
    return this.formGroup.controls;
  }

  onSubmit() {
    this.assigneeSelected.emit(this.f.assignee.value);
    this.modalRef.hide();
  }

  onClose(): void {
    this.modalRef.hide();
  }

}
