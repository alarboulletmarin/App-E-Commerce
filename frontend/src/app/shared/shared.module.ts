import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

const COMPONENTS: any = [];
const MODULES = [CommonModule];
@NgModule({
  declarations: [COMPONENTS],
  exports: [COMPONENTS],
  imports: [MODULES],
})
export class SharedModule {}
