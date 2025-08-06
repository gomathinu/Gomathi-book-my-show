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
  shows: any[] = [];

  constructor(private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {
    this.movieId = localStorage.getItem('selectedMovieId') ?? '';
    this.cinemaId = localStorage.getItem('selectedCinemaId') ?? '';

    if (this.movieId && this.cinemaId) {
      this.movieService.getShowsByMovieAndCinema(this.movieId, this.cinemaId).subscribe(data => {
        this.shows = data;
      });
    }
  }

  selectShow(show: any): void {
    localStorage.setItem('selectedShowId', show.id);
    this.router.navigate(['/seats']);
  }
}