// === Import : NPM === //
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// === Import : LOCAL ~ Components === //
import { HomePageComponent } from './home-page/home-page.component';
import { PlaceholderComponent } from './components/placeholder/placeholder.component';
import { CarouselComponent } from './components/carousel/carousel.component';

// === Import : LOCAL ~ Variables === //
import { APP_CONSTANTS } from 'src/app/app.constant';

// === Import : LOCAL ~ Modules === //
import { SharedModule } from 'src/app/shared/shared.module';

const homeRoutes: Routes = [
  { path: APP_CONSTANTS.routerLinks.home, component: HomePageComponent },
];

const MODULES = [SharedModule, RouterModule.forChild(homeRoutes)];

const COMPONENTS = [HomePageComponent];

@NgModule({
  declarations: [COMPONENTS, CarouselComponent, PlaceholderComponent],
  imports: [MODULES],
  exports: [RouterModule],
})
export class HomeModule {}
