import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { APP_CONSTANTS } from 'src/app/app.constant';
import { Category } from '../models/category.model';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  public endpoint = APP_CONSTANTS.endpoints.category;

  constructor(private httpClient: HttpClient) {}

  /**
   * Searches for categories based on a search term.
   * @param params The search term to use.
   * @returns An observable of the HTTP response containing an array of categories.
   */
  public searchCategory(params: string): Observable<HttpResponse<Category[]>> {
    return this.httpClient
      .get<Category[]>(this.endpoint.base(), {
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
   * Gets a list of all categories.
   * @returns An observable of an array of categories.
   */
  public getCategoryList(): Observable<Category[]> {
    return this.httpClient
      .get<Category[]>(this.endpoint.base(), { observe: 'response' })
      .pipe(
        catchError((error) => {
          return throwError(error);
        }),
        map((response: HttpResponse<Category[]>) => {
          return response.body!;
        })
      );
  }

  /**
   * Gets a category by its ID.
   * @param id The ID of the category to get.
   * @returns An observable of the category.
   */
  public getCategory(id: string): Observable<Category> {
    return this.httpClient.get<Category>(this.endpoint.base(id)).pipe(
      catchError((error) => {
        return throwError(error);
      })
    );
  }
}
