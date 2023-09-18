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

  public getCategory(id: string): Observable<Category> {
    return this.httpClient.get<Category>(this.endpoint.base(id)).pipe(
      catchError((error) => {
        return throwError(error);
      })
    );
  }
}
