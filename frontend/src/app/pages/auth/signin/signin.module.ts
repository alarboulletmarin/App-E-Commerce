import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/shared/shared.module';
import { SigninPageComponent } from './signin-page/signin-page.component';

const homeRoutes: Routes = [{ path: '', component: SigninPageComponent }];

const MODULES = [CommonModule, SharedModule, RouterModule.forChild(homeRoutes)];

const COMPONENTS = [SigninPageComponent];

@NgModule({
  declarations: [COMPONENTS],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SigninModule {}
