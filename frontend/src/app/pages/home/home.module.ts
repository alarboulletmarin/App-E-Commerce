import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { APP_CONSTANTS } from 'src/app/app.constant';
import { CarouselComponent } from './components/carousel/carousel.component';
import { PlaceholderComponent } from './components/placeholder/placeholder.component';

const homeRoutes: Routes = [
  { path: APP_CONSTANTS.routerLinks.home, component: HomePageComponent },
];

const MODULES = [CommonModule, SharedModule, RouterModule.forChild(homeRoutes)];

const COMPONENTS = [HomePageComponent];

@NgModule({
  declarations: [COMPONENTS, CarouselComponent, PlaceholderComponent],
  imports: [MODULES],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class HomeModule {}
