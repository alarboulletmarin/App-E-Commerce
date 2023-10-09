import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.sass'],
})
export class FormComponent {
  public isLoading = false;
  public signInForm: FormGroup;
  public errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.signInForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.signInForm.valid) {
      // Handle the valid form
      this.isLoading = true;
      this.authService.signin(this.signInForm.value).subscribe(
        () => {
          this.isLoading = false;
          this.router.navigate(['/']);
        },
        () => {
          this.isLoading = false;
        }
      );
    } else {
      // Handle the invalid form
      this.errorMessage = 'Please enter a valid username and password';
      this.signInForm.markAllAsTouched();
    }
  }
}
