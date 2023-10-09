// === Import : NPM === //
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// === Import : LOCAL ~ Components === //
import { SigninPageComponent } from './signin-page/signin-page.component';
import { FormComponent } from './components/form/form.component';

// === Import : LOCAL ~ Modules === //
import { SharedModule } from 'src/app/shared/shared.module';

const homeRoutes: Routes = [{ path: '', component: SigninPageComponent }];

const MODULES = [SharedModule, RouterModule.forChild(homeRoutes)];

const COMPONENTS = [SigninPageComponent, FormComponent];

@NgModule({
  declarations: [COMPONENTS],
  imports: [MODULES],
  exports: [RouterModule],
})
export class SigninModule {}
