import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
  private baseUrl = 'http://localhost:8081/bms/user/userDetails';

  constructor(private http: HttpClient) {}

  register(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user);
  }

  login(mobile: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, { mobile });
  }

  verifyOtp(mobile: string, otp: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/verify-otp`, { mobile, otp });
  }
}
