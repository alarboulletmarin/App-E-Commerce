import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductShort } from '../models/product.model';
import { Observable, catchError, map, tap, throwError } from 'rxjs';
import { APP_CONSTANTS } from 'src/app/app.constant';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  public endpoint = APP_CONSTANTS.endpoints.product;

  constructor(private httpClient: HttpClient) {}

  /**
   * Search for products based on a search term.
   * @param params - The search term to use.
   * @returns An observable that emits an HTTP response containing an array of ProductShort objects.
   */
  public searchProduct(
    params: string
  ): Observable<HttpResponse<ProductShort[]>> {
    return this.httpClient
      .get<ProductShort[]>(this.endpoint.base(), {
        observe: 'response',
        params: { searchTerm: params },
      })
      .pipe(
        catchError((error) => {
          return throwError(error);
        })
      );
  }

  /**
   * Retrieves a list of products.
   * @returns An observable of an array of ProductShort objects.
   */
  public getProductList(): Observable<ProductShort[]> {
    return this.httpClient
      .get<ProductShort[]>(this.endpoint.base(), { observe: 'response' })
      .pipe(
        tap(),
        catchError((error) => {
          return throwError(error);
        }),
        map((response: HttpResponse<ProductShort[]>) => {
          return response.body!;
        })
      );
  }
}
