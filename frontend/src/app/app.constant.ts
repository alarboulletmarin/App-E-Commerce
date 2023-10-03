import {
  faCube,
  faGlobe,
  faHome,
  faMoon,
  faShieldAlt,
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
  },
  routerLinks: {
    home: '',
    product: 'product',
    category: 'category',
    signin: 'signin',
    register: 'register',
  },
};
