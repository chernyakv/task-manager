import { Component, OnInit, Input } from '@angular/core';
import { UserService } from 'src/app/_services/user.service';
import { User } from '../../models/User';
import { Observable } from 'rxjs';
import { projection } from '@angular/core/src/render3';
import { Project } from 'src/app/modules/project/models/Project';

@Component({
  selector: 'app-users-on-the-project-table',
  templateUrl: './users-on-the-project-table.component.html',
  styleUrls: ['./users-on-the-project-table.component.less']
})
export class UsersOnTheProjectTableComponent implements OnInit {

  @Input() project: Project;

  public users: User[];

  public pageSize = 10;
  public currentPage = 0;
  public totalItems;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAllByProjectId(this.project.id, this.currentPage,this.pageSize, "id").subscribe(data => {
      this.users = data.content;
      this.totalItems = data.totalElements;      
    })
  }

  _openUserModal() {
    console.log(this.project);
  }




}
