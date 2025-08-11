import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-seat-selection',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './seat-selection.component.html',
  styleUrls: ['./seat-selection.component.less']
})
export class SeatSelectionComponent implements OnInit {
  showId: string | null = '';
  seats: any[] = [];
  selectedSeats: string[] = [];
  message = '';

  constructor(private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {
    this.showId = localStorage.getItem('selectedShowId');
    if (!this.showId) {
      this.message = 'Show not selected.';
      return;
    }

    this.movieService.getSeatsByShow(this.showId).subscribe({
      next: (res: any) => {
        this.seats = res;
      },
      error: () => {
        this.message = 'Failed to load seats.';
      }
    });
  }

  toggleSeat(seatNumber: string): void {
    if (this.selectedSeats.includes(seatNumber)) {
      this.selectedSeats = this.selectedSeats.filter(s => s !== seatNumber);
    } else {
      this.selectedSeats.push(seatNumber);
    }
  }

  confirmSeats(): void {
    if (this.selectedSeats.length === 0) {
      this.message = 'Please select at least one seat.';
      return;
    }

    localStorage.setItem('selectedSeats', JSON.stringify(this.selectedSeats));
    localStorage.setItem('seatCount', this.selectedSeats.length.toString());

    this.router.navigate(['/booking']);
  }
}