// === Import : NPM
import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

// === Import : LOCAL
import { APP_CONSTANTS } from 'src/app/app.constant';
import { AuthService } from '../services/auth.service';
import { DialogService } from '../services/dialog.service';

// If no token, do next without setting headers
// Else, TokenInterceptor intercepts all requests and set the new header with Bearer token.
// Furthermore, httpResponse header contains the new token to store.
@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const authToken = this.authService.currentTokenValue;
    let header = {};
    if (authToken) {
      header = {
        setHeaders: { Authorization: `Bearer ${authToken}` },
      };
    }

    const clone = req.clone(header);
    return next.handle(clone).pipe(
      tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          const token = event.headers.get(APP_CONSTANTS.headers.token);
          if (token) {
            this.authService.setToken(token);
          }
        }
      }),
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 || error.status === 403) {
          const errorMsg: string = error.error.message || error.error.reason;

          if (errorMsg === 'TokenExpired') {
            this.dialogService.openDialog(
              'Votre session a expiré. Veuillez vous reconnecter.'
            );
            // If token is expired, logout the user
            this.authService.logout();
          } else {
            // Handle other 401 reasons
            console.error('Unauthorized access:', errorMsg);
          }
        }
        return throwError(error);
      })
    );
  }
}
