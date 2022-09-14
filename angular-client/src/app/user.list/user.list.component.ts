import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user.list.component.html',
  styleUrls: ['./user.list.component.scss']
})
export class UserListComponent implements OnInit {
  data: User[] = [];
  displayedColumns: string[] = ['id', 'name', 'username', 'role', 'email'];
  isLoadingResults = true;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.userService.users().subscribe(
      (data) => {
        this.data = data;
        this.isLoadingResults = false;
      },
      (err) => {
        console.log(err);
        this.isLoadingResults = false;
      }
    );
  }

  logout() {
    this.userService.logout().subscribe((data) => {
      this.router.navigate(['login']);
    });
  }
}
