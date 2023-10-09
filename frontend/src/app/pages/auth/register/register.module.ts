import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/shared/shared.module';
import { RegisterPageComponent } from './register-page/register-page.component';
import { FormComponent } from './components/form/form.component';

const homeRoutes: Routes = [{ path: '', component: RegisterPageComponent }];

const MODULES = [CommonModule, SharedModule, RouterModule.forChild(homeRoutes)];

const COMPONENTS = [RegisterPageComponent];

@NgModule({
  declarations: [COMPONENTS, FormComponent],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class RegisterModule {}
