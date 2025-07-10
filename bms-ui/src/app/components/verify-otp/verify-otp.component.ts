import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.less']
})
export class VerifyOtpComponent {
  mobile = '';
  otp = '';

  constructor(private userService: UserService) {}

  verify() {
    this.userService.verifyOtp(this.mobile, this.otp).subscribe(response => {
      localStorage.setItem('token', response.token);
    });
  }
}
