import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './http-interceptors/token-interceptors';
import { ProductService } from './services/product.service';
import { CategoryService } from './services/category.service';
import { DialogService } from './services/dialog.service';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  imports: [MatDialogModule],
  providers: [
    ProductService,
    CategoryService,
    DialogService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
})
export class CoreModule {}
