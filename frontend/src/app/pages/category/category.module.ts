import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryPageComponent } from './category-page/category-page.component';
import { RouterModule, Routes } from '@angular/router';
import { APP_CONSTANTS } from 'src/app/app.constant';
import { SharedModule } from 'src/app/shared/shared.module';

const categoryRoutes: Routes = [
  { path: APP_CONSTANTS.routerLinks.home, component: CategoryPageComponent },
];

const MODULES = [
  CommonModule,
  SharedModule,
  RouterModule.forChild(categoryRoutes),
];

const COMPONENTS = [CategoryPageComponent];

@NgModule({
  declarations: [COMPONENTS],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class CategoryModule {}
