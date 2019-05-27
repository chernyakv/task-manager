import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Project } from '../../models/Project';
import { ProjectService } from 'src/app/services/project.service';
import { AuthService } from 'src/app/services/authentication.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { AlertService } from 'ngx-alerts';
import { retry } from 'rxjs/operators';

@Component({
  selector: 'app-new-project-modal',
  templateUrl: './new-project-modal.component.html',
  styleUrls: ['./new-project-modal.component.less']
})
export class NewProjectModalComponent implements OnInit {

  @Output() projectUpdated = new EventEmitter();
  submmited = false;
  editMode = false;
  formGroup : FormGroup;
  project: Project;
  editableProject: Project;


  constructor(private formBuilder: FormBuilder,
    private modalRef: BsModalRef,
    private projectService: ProjectService,
    private authService: AuthService,
    private alertService: AlertService) { }

  ngOnInit() {
    if(this.editMode){
      this.editableProject = Project.cloneBase(this.project);
      this.formGroup = this.formBuilder.group({
        projectCode: [this.project.code,[
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(10)
        ]],
        summary: [this.project.summary, [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(100)
        ]],
        name: [this.project.name,[
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30)
        ]]
      })
    } else{
      this.editableProject = new Project();
      this.formGroup = this.formBuilder.group({
        projectCode: ['', [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(10)
        ]],
        summary: ['', [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(100)
        ]],
        name: ['', [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30)
        ]]
      })
    }  
  }

  get f() {
    return this.formGroup.controls;
  }

  _onSubmit() {
    this.submmited = true;
    if(this.formGroup.invalid) {      
      return;
    }

    this.editableProject.code = this.f.projectCode.value;
    this.editableProject.name = this.f.name.value;
    this.editableProject.summary = this.f.summary.value;
    this.editableProject.manager = this.authService.currentUsername;
    this.projectService.createProject(this.editableProject).subscribe(()=>{
      this.projectUpdated.emit();
      this.modalRef.hide();
      const alertsMessage = this.editMode ? 'Project has been updated' : 'Project has been created';
      this.alertService.success(alertsMessage);
    }, (err) => {
      this.alertService.danger(err);        
    });      
       
  }
}
