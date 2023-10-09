import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/shared/shared.module';
import { SigninPageComponent } from './signin-page/signin-page.component';
import { FormComponent } from './components/form/form.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';

const homeRoutes: Routes = [{ path: '', component: SigninPageComponent }];

const MODULES = [
  CommonModule,
  SharedModule,
  RouterModule.forChild(homeRoutes),
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatButtonModule,
  MatDividerModule,
  ReactiveFormsModule,
  FormsModule,
  TranslateModule,
];

const COMPONENTS = [SigninPageComponent];

@NgModule({
  declarations: [COMPONENTS, FormComponent],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SigninModule {}
