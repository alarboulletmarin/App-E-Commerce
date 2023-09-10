import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCardComponent } from './components/product/product-card/product-card.component';

const COMPONENTS = [ProductCardComponent];
const MODULES = [CommonModule];
@NgModule({
  declarations: [COMPONENTS],
  exports: [COMPONENTS],
  imports: [MODULES],
})
export class SharedModule {}
