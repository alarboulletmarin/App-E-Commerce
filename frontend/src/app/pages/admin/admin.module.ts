import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from 'src/app/shared/shared.module';
import { AdminDetailsPageComponent } from './admin-details-page/admin-details-page.component';

const routes: Routes = [{ path: '', component: AdminDetailsPageComponent }];

const MODULES = [SharedModule, RouterModule.forChild(routes)];

const COMPONENTS = [AdminDetailsPageComponent];

@NgModule({
  declarations: [COMPONENTS],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AdminModule {}
