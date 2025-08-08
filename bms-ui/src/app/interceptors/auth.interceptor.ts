import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { v4 as uuidv4 } from 'uuid';

@Injectable({ providedIn: 'root' })
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //prelogin url no header addition
    if (
      req.url.includes('/login') ||
      req.url.includes('/register') ||
      req.url.includes('/verify-otp')
    ) {
      return next.handle(req);      
    }
    //postlogin url adding corrid, mobile, jwt token
    let correlationId = localStorage.getItem('X-Correlation-ID');
    if (!correlationId) {
      correlationId = uuidv4();
      localStorage.setItem('X-Correlation-ID', correlationId);
    }
    const token = localStorage.getItem('token');
    const mobile = localStorage.getItem('mobile');
    if (token) {
      req = req.clone({
        setHeaders: { Authorization: `Bearer ${token}`,
                      'X-Correlation-ID': correlationId,
                      'X-Mobile': mobile?? ''}
      });
    }
    return next.handle(req);
  }
}