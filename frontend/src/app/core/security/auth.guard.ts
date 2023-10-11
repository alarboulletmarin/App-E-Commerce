import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';

// authGuard pour les roles
export const authGuard: CanActivateFn = (route, state) => {
  return inject(AuthService).hasRoleUser();
};
