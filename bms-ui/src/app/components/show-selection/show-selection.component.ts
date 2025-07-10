import { Component } from '@angular/core';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-show-selection',
  templateUrl: './show-selection.component.html',
  styleUrls: ['./show-selection.component.less']
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
