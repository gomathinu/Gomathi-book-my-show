import { Component } from '@angular/core';
import { MovieService } from '../../services/movie.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-movie-search',
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class MovieSearchComponent {
  city = '';
  movies: any[] = [];

  constructor(private movieService: MovieService) {}

  search() {
    this.movieService.getMoviesByCity(this.city).subscribe(data => this.movies = data);
  }
}
