import {
  faCube,
  faGlobe,
  faHome,
  faMoon,
  faShieldAlt,
  faSun,
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
  },
  endpoints: {
    product: {
      base: (id?: string) =>
        `${environment.apiURL}/api/products${id ? `/${id}` : ''}`,
    },
  },
  routerLinks: {
    home: '',
    product: 'product',
  },
};
