import { Component } from '@angular/core';
import { UserService } from './services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(public userService: UserService, private router: Router) {}

  logout() {
    this.userService.logout().subscribe((data) => {
      this.router.navigate(['login']);
    });
  }
}
