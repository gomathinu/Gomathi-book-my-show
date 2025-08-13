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
  userId: string = '';
  totalAmount: number =0.0;

  constructor(private bookingService: BookingService, private router: Router) {}

  ngOnInit(): void {
    const showId = localStorage.getItem('selectedShowId');
    const seatCount = localStorage.getItem('seatCount');
    const selectedSeats = localStorage.getItem('selectedSeats');
    const userId = localStorage.getItem('userId');

    if (showId && seatCount && selectedSeats && userId) {
      this.showId = showId;
      this.seatCount = parseInt(seatCount);
      this.selectedSeats = JSON.parse(selectedSeats);
      this.userId = userId;
    } else {
      this.bookingMessage = 'Missing booking data.';
    }
  }

  confirmBooking(): void {
    const bookingPayload = {
      showId: this.showId,
      seatCount: this.seatCount,
      seatNumbers: this.selectedSeats,
      userId: this.userId
    };

    this.bookingService.initiateBooking(bookingPayload).subscribe({
      next: (res: any) => {
        this.bookingId = res.bookingId;
        localStorage.setItem('bookingId', this.bookingId);  
        this.totalAmount = res.totalAmount;
        localStorage.setItem('bookingAmount', this.totalAmount.toString());  
        this.router.navigate(['/payment']);
      },
      error: () => {
        this.bookingMessage = 'Failed to initiate booking.';
      }
    });
  }
}