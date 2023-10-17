import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { APP_CONSTANTS } from 'src/app/app.constant';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  public endpoint = APP_CONSTANTS.endpoints.user;

  constructor(private httpClient: HttpClient) {}

  /**
   * Retrieves a list of users from the API.
   * @returns An observable of the HTTP response containing the list of users.
   */
  public getUserList() {
    return this.httpClient.get(this.endpoint.base());
  }

  /**
   * Returns an observable of the user details.
   * @returns An observable of type User.
   */
  public getUserDetails(): Observable<User> {
    return this.httpClient.get<User>(this.endpoint.base());
  }
}
