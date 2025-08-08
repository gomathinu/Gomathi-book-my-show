import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-verify-otp',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.less']
})
export class VerifyOtpComponent {
  otp: string = '';
  mobile: string | null = localStorage.getItem('mobile');
  message: string = '';

  constructor(private userService: UserService, private router: Router) {}

  verifyOtp(): void {
    if (!this.otp || !this.mobile) {
      this.message = 'Mobile or OTP missing.';
      return;
    }
    localStorage.setItem('mobile',this.mobile);
    //TODO: here after service is called, before getting response and setting token, auth interceptor is called and header is set
    this.userService.verifyOtp(this.mobile, this.otp).subscribe({
      next: (res: any) => {
        if (res?.token) {
          localStorage.setItem('token', res.token);
          // Force token write to complete before navigating, else it results in 401 navigated without token set
          setTimeout(() => {
            this.router.navigate(['/movies']);
          }, 50);
        } else {
          this.message = 'Token missing in response.';
        }
      },
      error: () => {
        this.message = 'Invalid OTP. Try again.';
      }
  });
  }
}