import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-movie-search',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.less']
})
export class MovieSearchComponent implements OnInit {
  cities = ['Chennai', 'Mumbai', 'Bangalore'];
  selectedCity = '';
  movies: any[] = [];

  constructor(private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {}

  onCitySelect(event: Event): void {
    const selectedCity = (event.target as HTMLSelectElement)?.value || '';
    this.selectedCity = selectedCity;

    if (selectedCity) {
      this.movieService.getMoviesByCity(selectedCity).subscribe((data) => {
        this.movies = data;
      });
    } else {
      this.movies = [];
    }
  }

  selectMovie(movie: any): void {
    localStorage.setItem('selectedMovieId', movie.id);
    this.router.navigate(['/shows']);
  }
}