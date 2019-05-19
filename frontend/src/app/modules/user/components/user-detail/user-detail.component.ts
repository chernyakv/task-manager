import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user.service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../models/User';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.less']
})
export class UserDetailComponent implements OnInit {

  constructor(private activateRoute: ActivatedRoute,
              private userService: UserService) { }

  public user: User;

  name = 'Hello angular inline input';
  cost = 100;

  ngOnInit() {
    const username = this.activateRoute.snapshot.params.username;

    this.userService.getUserByUsername(username).subscribe(data => {
      this.user = data;
      console.log(this.user);
    });
  }

  saveUsername(value) {
    this.user.username = value;
    console.log(this.user);
  }

}
