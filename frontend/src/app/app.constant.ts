import { environment } from 'src/environments/environment';

export const APP_CONSTANTS = {
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
