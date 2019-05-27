import { Component, OnInit, Input } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from '../../models/User';
import { Observable, of } from 'rxjs';
import { projection } from '@angular/core/src/render3';
import { Project } from 'src/app/modules/project/models/Project';
import { mergeMap } from 'rxjs/operators';
import { TypeaheadMatch } from 'ngx-bootstrap';

@Component({
  selector: 'app-users-on-the-project-table',
  templateUrl: './users-on-the-project-table.component.html',
  styleUrls: ['./users-on-the-project-table.component.less']
})
export class UsersOnTheProjectTableComponent implements OnInit {
  @Input() project: Project;

  public selectedUser: string
  usersWithoutProject: User[];;
  public users: User[];

  public pageSize = 10;
  public currentPage = 1;
  public totalItems = 0;

  asyncSelected: string;
  typeaheadLoading: boolean;
  typeaheadNoResults: boolean;
  isSelected = false;

  constructor(private userService: UserService) {   
  }

  ngOnInit() {    
    this.updateUsers();
    this.userService.getAllWithoutProject(this.currentPage - 1, this.pageSize, 'id').subscribe(data => {
      this.usersWithoutProject = data.content;
      this.totalItems = data.totalElements;
    });
  }

  updateUsers() {
    this.userService.getAllByProjectId(this.project.id, ['DEVELOPER', 'TESTER'], this.currentPage - 1, this.pageSize, 'id').subscribe(data => {
      this.users = data.content;
      this.totalItems = data.totalElements;
    });
  } 

  public _pageChanged(event: any): void {
    this.currentPage = event.page;
    this.updateUsers();
  }
}
