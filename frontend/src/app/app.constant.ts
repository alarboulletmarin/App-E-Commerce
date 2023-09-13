import {
  faCube,
  faGlobe,
  faHome,
  faShieldAlt,
} from '@fortawesome/free-solid-svg-icons';
import { environment } from 'src/environments/environment';

export const APP_CONSTANTS = {
  colors: {},
  faIcon: {
    admin: faShieldAlt,
    home: faHome,
    item: faCube,
    language: faGlobe,
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
