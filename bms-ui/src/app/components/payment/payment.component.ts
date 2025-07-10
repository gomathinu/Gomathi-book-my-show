import { Component } from '@angular/core';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.less']
})
export class PaymentComponent {
  paymentDetails = { bookingId: '', amount: 0 };

  constructor(private paymentService: PaymentService) {}

  pay() {
    this.paymentService.initiatePayment(this.paymentDetails).subscribe(console.log);
  }
}
