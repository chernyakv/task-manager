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


  asyncSelected: string;
  typeaheadLoading: boolean;
  typeaheadNoResults: boolean;
  dataSource: Observable<User[]>;


  @Input() project: Project;

  public selectedUser: string;
  public users: User[];
  public usersWithoutProject: User[];

  public pageSize = 10;
  public currentPage = 0;
  public totalItems;

  constructor(private userService: UserService) {
    this.dataSource = Observable.create((observer: any) => {
      // Runs on every search
      observer.next(this.asyncSelected);
    })
      .pipe(
        mergeMap((token: string) => this.getStatesAsObservable(token))
      );
  }

  ngOnInit() {
    this.userService.getAllByProjectId(this.project.id, ['DEVELOPER', 'TESTER'], this.currentPage, this.pageSize, 'id').subscribe(data => {
      this.users = data.content;
      this.totalItems = data.totalElements;
    });

    this.userService.getAllWithoutProject(this.currentPage, this.pageSize, 'id').subscribe(data => {
      this.usersWithoutProject = data.content;
      this.totalItems = data.totalElements;
    });
  }

  _addUser() {
    this.userService.getUserByUsername(this.selectedUser).subscribe(data => {
      const user = data;
      user.projectId = this.project.id;
      this.userService.saveUser(user).subscribe(data => {
        this.userService.getAllByProjectId(this.project.id,['DEVELOPER'], this.currentPage,  this.pageSize, 'id').subscribe(data => {
          this.users = data.content;
          this.totalItems = data.totalElements;
        });
      });

    });
  }

  _openUserModal() {
    console.log(this.project);
  }


  getStatesAsObservable(token: string): Observable<User[]> {
    const query = new RegExp(token, 'i');

    return of(
      this.users.filter((state: User) => {
        return query.test(state.username);
      })
    );
  }

  changeTypeaheadLoading(e: boolean): void {
    this.typeaheadLoading = e;
  }
}
