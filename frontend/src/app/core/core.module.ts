import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './http-interceptors/token-interceptors';
import { ProductService } from './services/product.service';
import { CategoryService } from './services/category.service';
import { MatDialogModule } from '@angular/material/dialog';
import { ToastService } from './services/toast.service';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  imports: [MatDialogModule, MatSnackBarModule],
  providers: [
    ProductService,
    CategoryService,
    ToastService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
})
export class CoreModule {}
