import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-new-project-modal',
  templateUrl: './new-project-modal.component.html',
  styleUrls: ['./new-project-modal.component.less']
})
export class NewProjectModalComponent implements OnInit {

  newProjectForm : FormGroup;

  constructor(private formBuilder: FormBuilder,
    private modalRef: BsModalRef) { }

  ngOnInit() {
    this.newProjectForm = this.formBuilder.group({
      projectCode: ['', Validators.required],
      summary: ['', Validators.required],
    })
  }

  get f() {
    return this.newProjectForm.controls;
  }

}
