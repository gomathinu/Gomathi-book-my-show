import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingService } from '../../services/booking.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.less']
})
export class BookingComponent implements OnInit {
  showId: string = '';
  seatCount: number = 0;
  selectedSeats: string[] = [];
  bookingMessage: string = '';
  bookingId: string = '';

  constructor(private bookingService: BookingService, private router: Router) {}

  ngOnInit(): void {
    const showId = localStorage.getItem('showId');
    const seatCount = localStorage.getItem('seatCount');
    const selectedSeats = localStorage.getItem('selectedSeats');

    if (showId && seatCount && selectedSeats) {
      this.showId = showId;
      this.seatCount = parseInt(seatCount);
      this.selectedSeats = JSON.parse(selectedSeats);
    } else {
      this.bookingMessage = 'Missing booking data.';
    }
  }

  confirmBooking(): void {
    const bookingPayload = {
      showId: this.showId,
      seatCount: this.seatCount,
      seatNumbers: this.selectedSeats
    };

    this.bookingService.initiateBooking(bookingPayload).subscribe({
      next: (res: any) => {
        this.bookingId = res.bookingId;
        localStorage.setItem('bookingId', this.bookingId);
        this.router.navigate(['/payment']);
      },
      error: () => {
        this.bookingMessage = 'Failed to initiate booking.';
      }
    });
  }
}