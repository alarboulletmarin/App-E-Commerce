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

  public getUserList() {
    return this.httpClient.get(this.endpoint.base());
  }

  public getUserDetails(): Observable<User> {
    return this.httpClient.get<User>(this.endpoint.base());
  }
}
