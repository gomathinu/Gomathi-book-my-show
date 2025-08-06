import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'register', loadComponent: () => import('./components/register/register.component').then(m => m.RegisterComponent) },
  { path: 'login', loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent) },
  { path: 'verify-otp', loadComponent: () => import('./components/verify-otp/verify-otp.component').then(m => m.VerifyOtpComponent) },
  { path: 'movies', loadComponent: () => import('./components/movie-search/movie-search.component').then(m => m.MovieSearchComponent) },
  { path: 'shows', loadComponent: () => import('./components/show-selection/show-selection.component').then(m => m.ShowSelectionComponent) },
  { path: 'seats', loadComponent: () => import('./components/seat-selection/seat-selection.component').then(m => m.SeatSelectionComponent) },
  { path: 'booking', loadComponent: () => import('./components/booking/booking.component').then(m => m.BookingComponent) },
  { path: 'payment', loadComponent: () => import('./components/payment/payment.component').then(m => m.PaymentComponent) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}