import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { VerifyOtpComponent } from './components/verify-otp/verify-otp.component';
import { MovieSearchComponent } from './components/movie-search/movie-search.component';
import { ShowSelectionComponent } from './components/show-selection/show-selection.component';
import { SeatSelectionComponent } from './components/seat-selection/seat-selection.component';
import { BookingComponent } from './components/booking/booking.component';
import { PaymentComponent } from './components/payment/payment.component';

export const routes: Routes = [
  { path: '', redirectTo: 'movies', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'verify-otp', component: VerifyOtpComponent },
  { path: 'movies', component: MovieSearchComponent },
  { path: 'shows', component: ShowSelectionComponent },
  { path: 'seats', component: SeatSelectionComponent },
  { path: 'booking', component: BookingComponent },
  { path: 'payment', component: PaymentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}