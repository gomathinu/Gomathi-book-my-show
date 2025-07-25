import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SeatLockRequest {
  showId: number;
  seatNumbers: string[];
  userId: number;
}

@Injectable({ providedIn: 'root' })
export class MovieService {
  private baseUrl = 'http://localhost:8082/bms/movie/movieDetails';

  constructor(private http: HttpClient) {}

  getMoviesByCity(cityName: string): Observable<any> {
    const params = new HttpParams().set('cityName', cityName);
    return this.http.get<any[]>(`${this.baseUrl}/movies-by-city`, { params });
  }

  /*getMoviesByCinema(cinemaName: string): Observable<any> {
    const params = new HttpParams().set('cinemaName', cinemaName);
    return this.http.get<any[]>(`${this.baseUrl}/movies-by-cinema`, { params });
  }*/

  getShowsByMovieAndCinema(movieName: string, cinemaName: string): Observable<any> {
    const params = new HttpParams();
    params.set('movieName', movieName);
    params.set('cinemaName', cinemaName);
    return this.http.get<any[]>(`${this.baseUrl}/shows-by-movie-cinema`, { params });
  }

  getSeatsByShow(showId: string): Observable<any> {
    const params = new HttpParams().set('showId', showId);
    return this.http.get<any[]>(`${this.baseUrl}/seats-by-show`, { params });
  }

  lockSeats(request: SeatLockRequest): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/lock-seats`, request);
  }

}
