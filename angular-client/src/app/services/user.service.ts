import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from '../models/user';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

const API_URL = 'http://localhost/api/user/service/';

@Injectable()
export class UserService {
  isLoggedIn = false;

  constructor(private http: HttpClient, private router: Router) {}

  login(data: any): Observable<any> {
    const headers = {
      authorization: 'Basic ' + window.btoa(data.username + ':' + data.password)
    };
    return this.http.get(API_URL + 'login', { headers: headers }).pipe(
      tap((_) => (this.isLoggedIn = true)),
      catchError(this.handleError('login', []))
    );
  }

  logout(): Observable<any> {
    return this.http.post(API_URL + 'logout', {}).pipe(
      tap((_) => (this.isLoggedIn = false)),
      catchError(this.handleError('logout', []))
    );
  }

  register(data: any): Observable<any> {
    return this.http
      .post<User>(API_URL + 'register', data)
      .pipe(catchError(this.handleError('register', [])));
  }

  users(): Observable<User[]> {
    return this.http.get<User[]>(API_URL + 'users').pipe(catchError(this.handleError('users', [])));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      if (error.status === 401 && this.isLoggedIn) {
        this.logout().subscribe((data) => {
          this.router.navigate(['login']);
        });
      }
      return of(result as T);
    };
  }
}
