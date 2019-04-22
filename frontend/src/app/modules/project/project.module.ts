import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewProjectModalComponent } from './components/new-project-modal/new-project-modal.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    NewProjectModalComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,   
    FormsModule,
    BrowserModule,
    RouterModule,  
    HttpClientModule
  ]
  
})
export class ProjectModule { }
