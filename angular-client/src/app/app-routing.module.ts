import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserListComponent } from './user.list/user.list.component';
import { LoginGuard } from './login/login.guard';

const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent, data: { title: 'Register' } },
  { path: 'login', component: LoginComponent, data: { title: 'Login' } },
  {
    path: 'users',
    canActivate: [LoginGuard],
    component: UserListComponent,
    data: { title: 'List of users' }
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
