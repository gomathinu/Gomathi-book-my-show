import { Component } from '@angular/core';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-movie-search',
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.less']
})
export class MovieSearchComponent {
  city = '';
  movies: any[] = [];

  constructor(private movieService: MovieService) {}

  search() {
    this.movieService.getMoviesByCity(this.city).subscribe(data => this.movies = data);
  }
}
