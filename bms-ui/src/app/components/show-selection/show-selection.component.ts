import { Component } from '@angular/core';
import { MovieService } from '../../services/movie.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-show-selection',
  templateUrl: './show-selection.component.html',
  styleUrls: ['./show-selection.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class ShowSelectionComponent {
  movie = '';
  cinema = '';
  shows: any[] = [];

  constructor(private movieService: MovieService) {}

  getShows() {
    this.movieService.getShowsByMovieAndCinema(this.movie, this.cinema)
      .subscribe(data => this.shows = data);
  }
}
