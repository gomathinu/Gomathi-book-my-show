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
  userId: string = '';
  bookingAmount: number = 0.0;

  constructor(private paymentService: PaymentService, private router: Router) {}

  ngOnInit(): void {
    const bookingId = localStorage.getItem('bookingId');
    const userId = localStorage.getItem('userId');
    const bookingAmount = localStorage.getItem('bookingAmount');
    if (bookingId) {
      this.bookingId = bookingId;
    } else {
      this.statusMessage = 'Missing booking ID.';
    }
    if(userId){
      this.userId = userId;
    }
    if(bookingAmount){
      this.bookingAmount = parseFloat(bookingAmount);
    }
  }

  initiatePayment(): void {
    this.showSpinner = true;

    const payload = {
      bookingId: this.bookingId,
      userId: this.userId,
      totalAmount: this.bookingAmount
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
          localStorage.removeItem('selectedShowId');
        }
      },
      error: () => {
        this.statusMessage = 'Payment failed. Please try again.';
        this.showSpinner = false;
      }
    });
  }
}