import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UsersComponent } from './components/users/users.component';
import { NewUserModalComponent } from './components/new-user-modal/new-user-modal.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { UsersOnTheProjectTableComponent } from './components/users-on-the-project-table/users-on-the-project-table.component';
import { TypeaheadModule } from 'ngx-bootstrap';




@NgModule({
  declarations: [
    UsersComponent,
    NewUserModalComponent,
    UsersOnTheProjectTableComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,   
    FormsModule,
    BrowserModule,
    RouterModule,  
    HttpClientModule,
    PaginationModule.forRoot(),
    TypeaheadModule.forRoot(),   
  ],
  exports: [UsersComponent, UsersOnTheProjectTableComponent],
  entryComponents:[NewUserModalComponent]
})
export class UserModule { }
