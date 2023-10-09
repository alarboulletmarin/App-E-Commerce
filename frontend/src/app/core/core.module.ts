import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './http-interceptors/token-interceptors';
import { ProductService } from './services/product.service';
import { CategoryService } from './services/category.service';

@NgModule({
  providers: [
    ProductService,
    CategoryService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
})
export class CoreModule {}
