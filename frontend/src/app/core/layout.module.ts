import { NgModule } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [NavbarComponent, FooterComponent],
  imports: [SharedModule],
  exports: [NavbarComponent, FooterComponent],
})
export class LayoutModule {}
