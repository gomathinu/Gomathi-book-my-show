import { Component } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class BookingComponent {
  bookingData = { userId: '', showId: '', seats: [] };

  constructor(private bookingService: BookingService) {}

  book() {
    this.bookingService.initiateBooking(this.bookingData).subscribe(console.log);
  }
}
