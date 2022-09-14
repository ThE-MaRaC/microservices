import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  FormBuilder,
  FormGroup,
  NgForm,
  Validators
} from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { ErrorStateMatcher } from '@angular/material/core';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  name = '';
  email = '';
  password = '';
  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  onFormSubmit(form: NgForm) {
    this.userService.register(form).subscribe(
      (res) => {
        this.router.navigate(['login']);
      },
      (err) => {
        console.log(err);
        alert(err.error);
      }
    );
  }
}
