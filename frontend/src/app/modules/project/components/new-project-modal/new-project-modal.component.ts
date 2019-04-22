import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Project } from '../../models/Project';
import { ProjectService } from 'src/app/_services/project.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-new-project-modal',
  templateUrl: './new-project-modal.component.html',
  styleUrls: ['./new-project-modal.component.less']
})
export class NewProjectModalComponent implements OnInit {

  private subscriptions: Subscription[] = [];
  submmited = false;
  newProjectForm : FormGroup;
  project: Project;

  constructor(private formBuilder: FormBuilder,
    private modalRef: BsModalRef,
    private projectService: ProjectService,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    if(!this.project){
      this.project = new Project();
    }
    
    this.newProjectForm = this.formBuilder.group({
      projectCode: ['', Validators.required],
      summary: ['', Validators.required],
    })
  }

  get f() {
    return this.newProjectForm.controls;
  }

  _onSubmit() {
    this.submmited = true;

    if(this.newProjectForm.valid){
      this.project.manager = this.authenticationService.currentUsername;
      this.subscriptions.push(this.projectService.createProject(this.project).subscribe(()=>{

      }))
      this.modalRef.hide();
    }    
  }

  ngOnDestoroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
