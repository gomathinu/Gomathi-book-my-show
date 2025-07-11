import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BookingService {
  private baseUrl = 'http://localhost:8083/bms/booking/bookingDetails';

  constructor(private http: HttpClient) {}

  initiateBooking(payload: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/initiateBooking`, payload);
  }
}
