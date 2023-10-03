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
  // CATEGORY PAGE
  {
    path: APP_CONSTANTS.routerLinks.category,
    pathMatch: 'full',
    loadChildren: () =>
      import('./pages/category/category.module').then((m) => m.CategoryModule),
  },
  // AUTH PAGE
  // SIGNIN
  {
    path: APP_CONSTANTS.routerLinks.signin,
    pathMatch: 'full',
    loadChildren: () =>
      import('./pages/auth/signin/signin.module').then((m) => m.SigninModule),
  },
  // REGISTER
  {
    path: APP_CONSTANTS.routerLinks.register,
    pathMatch: 'full',
    loadChildren: () =>
      import('./pages/auth/register/register.module').then(
        (m) => m.RegisterModule
      ),
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
