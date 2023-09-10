import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductPageComponent } from './product-page/product-page.component';
import { Routes, RouterModule } from '@angular/router';
import { APP_CONSTANTS } from 'src/app/app.constant';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProductListComponent } from './components/product-list/product-list.component';

const productRoutes: Routes = [
  { path: APP_CONSTANTS.routerLinks.home, component: ProductPageComponent },
];

const MODULES = [
  CommonModule,
  SharedModule,
  RouterModule.forChild(productRoutes),
];

const COMPONENTS = [ProductPageComponent];

@NgModule({
  declarations: [COMPONENTS, ProductListComponent],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ProductModule {}
