import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { UserDetailsPageComponent } from './user-details-page/user-details-page.component';
import { SharedModule } from 'src/app/shared/shared.module';

const routes: Routes = [{ path: '', component: UserDetailsPageComponent }];

const MODULES = [SharedModule, RouterModule.forChild(routes)];

const COMPONENTS = [UserDetailsPageComponent];

@NgModule({
  declarations: [COMPONENTS],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class UserModule {}
