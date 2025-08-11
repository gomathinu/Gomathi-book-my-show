import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-show-selection',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './show-selection.component.html',
  styleUrls: ['./show-selection.component.less']
})
export class ShowSelectionComponent implements OnInit {
  movieId: string = '';
  cinemaId: string = '';
  movieTitle: string = '';
  cinemaName: string = '';
  shows: any[] = [];

  constructor(private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {
    this.movieId = localStorage.getItem('selectedMovieId') ?? '';
    this.cinemaId = localStorage.getItem('selectedCinemaId') ?? '';
    this.movieTitle = localStorage.getItem('selectedMovieTitle') ?? '';
    this.cinemaName = localStorage.getItem('selectedCinemaName') ?? '';

    if (this.movieId && this.cinemaId) {
      this.movieService.getShowsByMovieAndCinema(this.movieTitle, this.cinemaName).subscribe(data => {
        this.shows = data;
      });
    }
  }

  selectShow(show: any): void {
    localStorage.setItem('selectedShowId', show.showId);
    this.router.navigate(['/seats']);
  }
}