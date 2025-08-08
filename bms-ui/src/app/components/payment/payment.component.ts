import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaymentService } from '../../services/payment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.less']
})
export class PaymentComponent implements OnInit {
  bookingId: string = '';
  statusMessage: string = '';
  showSpinner = false;

  constructor(private paymentService: PaymentService, private router: Router) {}

  ngOnInit(): void {
    const bookingId = localStorage.getItem('bookingId');
    if (bookingId) {
      this.bookingId = bookingId;
    } else {
      this.statusMessage = 'Missing booking ID.';
    }
  }

  initiatePayment(): void {
    this.showSpinner = true;

    const payload = {
      bookingId: this.bookingId
    };

    this.paymentService.initiatePayment(payload).subscribe({
      next: (res: any) => {
        this.statusMessage = `Payment ${res.status}`;
        this.showSpinner = false;
        //check if we are getting success only, not pending... else we have to poll for payment status after initiate payment call and have separate rest call for it
        if (res.status === 'SUCCESS') {
          // Clear localStorage after success
          localStorage.removeItem('bookingId');
          localStorage.removeItem('selectedSeats');
          localStorage.removeItem('seatCount');
          localStorage.removeItem('showId');
        }
      },
      error: () => {
        this.statusMessage = 'Payment failed. Please try again.';
        this.showSpinner = false;
      }
    });
  }
}