import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PaymentService {
  private baseUrl = 'http://bms-payment-service:8080/bms/payment/paymentDetails';

  constructor(private http: HttpClient) {}

  initiatePayment(details: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/initiatePayment`, details);
  }
}
