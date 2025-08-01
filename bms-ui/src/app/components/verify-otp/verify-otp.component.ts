import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.less'],
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule]
})
export class VerifyOtpComponent {
  mobile = '';
  otp = '';

  constructor(private userService: UserService) {}

  verify() {
    localStorage.setItem('mobile',this.mobile);
    this.userService.verifyOtp(this.mobile, this.otp).subscribe(response => {
      localStorage.setItem('token', response.token);
    });
  }
}
