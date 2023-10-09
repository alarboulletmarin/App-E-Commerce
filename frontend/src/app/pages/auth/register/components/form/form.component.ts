import { Component } from '@angular/core';
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
  public registerForm: FormGroup;
  public errorMessage: string = '';
  public currentStep: number = 1;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      step1: this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        phone: [''],
      }),
      step2: this.fb.group({
        address: [''],
        city: [''],
        zipCode: [''],
      }),
      step3: this.fb.group({
        username: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(8)]],
      }),
    });
  }

  get step1Group(): FormGroup {
    return this.registerForm.get('step1') as FormGroup;
  }

  get step2Group(): FormGroup {
    return this.registerForm.get('step2') as FormGroup;
  }

  get step3Group(): FormGroup {
    return this.registerForm.get('step3') as FormGroup;
  }

  getCurrentStepGroup(): FormGroup | undefined {
    switch (this.currentStep) {
      case 1:
        return this.step1Group;
      case 2:
        return this.step2Group;
      case 3:
        return this.step3Group;
      default:
        return undefined;
    }
  }

  public nextStep() {
    const currentStepForm = this.registerForm.get(`step${this.currentStep}`);
    if (currentStepForm && currentStepForm.valid) {
      if (this.currentStep < 3) this.currentStep++;
    } else {
      currentStepForm?.markAllAsTouched();
      this.setErrorMessageForStep();
    }
  }

  public previousStep() {
    if (this.currentStep > 1) this.currentStep--;
  }

  get consolidatedData() {
    const { step1, step2, step3 } = this.registerForm.value;
    return { ...step1, ...step2, ...step3, roleId: '1' };
  }

  public setErrorMessageForStep() {
    switch (this.currentStep) {
      case 1:
        this.errorMessage = 'Please fill out all required fields in Step 1';
        break;
      case 2:
        this.errorMessage = 'Please check your address details in Step 2';
        break;
      case 3:
        this.errorMessage =
          'Please enter a valid username and password in Step 3';
        break;
      default:
        this.errorMessage = 'An error occurred, please try again.';
    }
  }

  public onSubmit() {
    if (this.registerForm.valid) {
      // Handle the valid form
      this.isLoading = true;
      this.authService.register(this.consolidatedData).subscribe(
        () => {
          this.isLoading = false;
          this.authService.signin(this.consolidatedData).subscribe();
          this.router.navigate(['/']);
        },
        (error) => {
          this.isLoading = false;
          this.errorMessage = error.message;
        }
      );
    } else {
      this.setErrorMessageForStep();
      this.registerForm.markAllAsTouched();
    }
  }
}
