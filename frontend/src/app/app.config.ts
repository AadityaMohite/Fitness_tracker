import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors } from '@angular/common/http';

import { routes } from './app.routes';
import { en_US, provideNzI18n } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { AuthInterceptor } from './Service/auth-interceptor';
import { authInterceptor } from './interceptors/auth-interceptor';

registerLocaleData(en);

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideNzI18n(en_US),
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },

     provideHttpClient(
      withInterceptors([
        authInterceptor
      ])
    )
  ],
};
