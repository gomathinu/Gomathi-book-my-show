import { Component } from '@angular/core';
import { MovieService, SeatLockRequest } from '../../services/movie.service';

@Component({
  selector: 'app-seat-selection',
  templateUrl: './seat-selection.component.html',
  styleUrls: ['./seat-selection.component.less']
})
export class SeatSelectionComponent {

  showId: number = 0;
  seatNumbersInput: string = '';
  userId: number = 0;
  lockStatus: string = '';

  constructor(private movieService: MovieService) {}

  lockSeats(): void {
    const seatNumbers = this.seatNumbersInput.split(',').map(seat => seat.trim());

    const request: SeatLockRequest = {
      showId: this.showId,
      seatNumbers: seatNumbers,
      userId: this.userId
    };

    this.movieService.lockSeats(request).subscribe({
      next: (success) => {
        this.lockStatus = success ? 'Seats successfully locked!' : 'Failed to lock seats.';
      },
      error: () => {
        this.lockStatus = 'Error occurred while locking seats.';
      }
    });
  }
}
