import { Component } from '@angular/core';
import { PaymentService } from '../../services/payment.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class PaymentComponent {
  paymentDetails = { bookingId: '', amount: 0 };

  constructor(private paymentService: PaymentService) {}

  pay() {
    this.paymentService.initiatePayment(this.paymentDetails).subscribe(console.log);
  }
}
