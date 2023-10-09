// === Import : NPM === //
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// === Import : LOCAL ~ Components === //
import { RegisterPageComponent } from './register-page/register-page.component';
import { FormComponent } from './components/form/form.component';

// === Import : LOCAL ~ Modules === //
import { SharedModule } from 'src/app/shared/shared.module';

const homeRoutes: Routes = [{ path: '', component: RegisterPageComponent }];

const MODULES = [SharedModule, RouterModule.forChild(homeRoutes)];

const COMPONENTS = [RegisterPageComponent, FormComponent];

@NgModule({
  declarations: [COMPONENTS],
  imports: [MODULES],
  exports: [RouterModule],
})
export class RegisterModule {}
