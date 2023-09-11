import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { APP_CONSTANTS } from './app.constant';
import { PageNotFoundComponent } from './pages/pages-not-found/page-not-found.component';

const routes: Routes = [
  // HOME PAGE
  {
    path: APP_CONSTANTS.routerLinks.home,
    pathMatch: 'full',
    loadChildren: () =>
      import('./pages/home/home.module').then((m) => m.HomeModule),
  },
  // PRODUCT PAGE
  {
    path: APP_CONSTANTS.routerLinks.product,
    pathMatch: 'full',
    loadChildren: () =>
      import('./pages/product/product.module').then((m) => m.ProductModule),
  },

  // ERROR PAGE
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppRoutingModule {}
