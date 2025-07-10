import { Component } from '@angular/core';
import { BookingService } from '../../services/booking.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.less']
})
export class BookingComponent {
  bookingData = { userId: '', showId: '', seats: [] };

  constructor(private bookingService: BookingService) {}

  book() {
    this.bookingService.initiateBooking(this.bookingData).subscribe(console.log);
  }
}
