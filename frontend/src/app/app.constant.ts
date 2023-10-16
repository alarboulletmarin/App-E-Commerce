import {
  faCube,
  faGlobe,
  faHeart,
  faHome,
  faMoon,
  faShieldAlt,
  faShoppingCart,
  faSignInAlt,
  faSun,
  faUser,
} from '@fortawesome/free-solid-svg-icons';
import { environment } from 'src/environments/environment';

export const APP_CONSTANTS = {
  colors: {},
  faIcon: {
    admin: faShieldAlt,
    home: faHome,
    item: faCube,
    language: faGlobe,
    dark_theme: faMoon,
    light_theme: faSun,
    user: faUser,
    shoppingCart: faShoppingCart,
    heart: faHeart,
    signOutAlt: faSignInAlt,
  },
  endpoints: {
    product: {
      base: (id?: string) =>
        `${environment.apiURL}/api/products${id ? `/${id}` : ''}`,
    },
    category: {
      base: (id?: string) =>
        `${environment.apiURL}/api/categories${id ? `/${id}` : ''}`,
    },
    auth: {
      base: () => `${environment.apiURL}/api/auth`,
      signin: {
        base: () => `${environment.apiURL}/api/auth/signin`,
      },
      register: {
        base: () => `${environment.apiURL}/api/auth/register`,
      },
    },
    user: {
      base: () => `${environment.apiURL}/api/users/current`,
    },
  },
  routerLinks: {
    home: '',
    product: 'product',
    category: 'category',
    signin: 'signin',
    register: 'register',
    user: 'user',
    admin: 'admin',
  },
  headers: {
    token: 'X-App-Session-Token',
  },
};
