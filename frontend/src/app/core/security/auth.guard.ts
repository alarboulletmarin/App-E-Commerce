// === Import : NPM
import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';

// === Import : LOCAL
import { AuthService } from '../services/auth.service';

/**
 * A guard that checks if the user is authorized to access a certain route based on their role.
 * @param route - The route to be activated.
 * @param state - The current state of the router.
 * @returns A boolean indicating whether the user is authorized to access the route.
 */
export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const expectedRole = route.data['expectedRole'];
  const isAuthorized = authService.hasRole(expectedRole);
  return isAuthorized;
};
